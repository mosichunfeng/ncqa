package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.config.shiro.CustomerAuthenticationToken;
import cn.neusoft.xuxiao.config.shiro.LoginType;
import cn.neusoft.xuxiao.config.shiro.Principal;
import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.Admin;
import cn.neusoft.xuxiao.service.IResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
public class MainController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private IResourceService resourceService;

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginForm(){
        return "login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String loginPost(@Valid Admin validAdmin, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "redirect:/login";
        }
        String username = validAdmin.getUsername();
        CustomerAuthenticationToken token=new CustomerAuthenticationToken(username,validAdmin.getPassword(), LoginType.ADMIN.getType());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            addFlashMessage(redirectAttributes,new Message(Message.Type.error,  "未知账户"));
            return  redirect("login");
        }catch(IncorrectCredentialsException ice){
            addFlashMessage(redirectAttributes,new Message(Message.Type.error,  "密码不正确"));
            return  redirect("login");
        }catch(LockedAccountException lae){
            addFlashMessage(redirectAttributes,new Message(Message.Type.error,  "账户已锁定"));
            return  redirect("login");
        }catch(ExcessiveAttemptsException eae){
            addFlashMessage(redirectAttributes,new Message(Message.Type.error,  "用户名或密码错误次数过多"));
            return  redirect("login");
        }catch(AuthenticationException ae){
            ae.printStackTrace();
            addFlashMessage(redirectAttributes,new Message(Message.Type.error,  "用户名或密码不正确"));
            return  redirect("login");
        }
        SecurityUtils.getSubject().getSession().setAttribute("loginType", LoginType.ADMIN.getType());
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("LoginUser",SecurityUtils.getSubject().getPrincipal());
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return "redirect:/index";
        }else{
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping(value = "index")
    public String index(Model model){
        Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("menus",resourceService.selectAdminMenu(principal.getId()));
        return "index";
    }


    @GetMapping("dashboard")
    public String dashboard() throws Exception{
        return "view/dashboard";
    }
}
