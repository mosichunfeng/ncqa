package cn.neusoft.xuxiao.controller.admin;


import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.service.IClassInfoService;
import cn.neusoft.xuxiao.service.IRegisterService;
import com.magicbeans.base.Pages;
import com.magicbeans.base.db.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IRegisterService registerService;

    @Autowired
    private IClassInfoService classInfoService;

    @RequestMapping("/list")
    public String list(Pages page, Model model) {
        List<Filter> filters = new ArrayList();
        filters.add(Filter.eq("status", 0));
        page = registerService.findPage(page, filters, null);
        model.addAttribute("page", page);
        model.addAttribute("classinfo", classInfoService.findAll());
        return "view/register/list";
    }

    /*
     * 跳转设置实习班级页面
     */
    @RequestMapping("/toIntern")
    public String toIntern(Model model) {
        model.addAttribute("classinfo", classInfoService.findAll());
        return "view/register/intern";
    }

    /*
     *保存实习班级
     */
    @RequestMapping("/saveIntern")
    private String saveIntern(String classes) {
        return null;
    }
}
