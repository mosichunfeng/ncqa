package cn.neusoft.xuxiao.controller;

import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.common.freemarker.directive.FlashMessageDirective;
import cn.neusoft.xuxiao.config.shiro.Principal;
import cn.neusoft.xuxiao.entity.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cn.neusoft.xuxiao.service.IAdminService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Controller的基础类
 */
public class BaseController {

    @Autowired
    private Validator validator;

    @Autowired
    private IAdminService adminService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 重定向到某个资源
     *
     * @param resource
     * @return
     */
    protected String redirect(String resource) {
        return "redirect:" + resource;
    }

    /**
     * 转发到某个资源
     *
     * @param resource
     * @return
     */
    protected String forward(String resource) {
        return "forward:" + resource;
    }

    /**
     * 数据验证
     *
     * @param target 验证对象
     * @param groups 验证组
     * @return 验证结果
     */
    protected boolean isValid(Object target, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 数据验证
     *
     * @param type     类型
     * @param property 属性
     * @param value    值
     * @param groups   验证组
     * @return 验证结果
     */
    protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
        Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前用户是否授权
     * @return
     */
    public boolean isAuthenticated() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return subject.isAuthenticated();
        }
        return false;
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    public Admin getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return adminService.find(principal.getId());
            }
        }
        return null;
    }

    /**
     * 获取当前登录人的Id
     * @return
     */
    public String getLoginUserId(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal.getId();
            }
        }
        return null;
    }

    /**
     * 获取当前登录用户的用户名
     * @return
     */
    public String getLoginUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal.getUsername();
            }
        }
        return null;
    }



    /**
     * 添加瞬时消息
     *
     * @param redirectAttributes
     *            RedirectAttributes
     * @param message
     *            消息
     */
    protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
        if (redirectAttributes != null && message != null) {
            redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


}
