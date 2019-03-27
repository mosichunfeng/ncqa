package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourcesController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping("/list")
    public String list(){
        return null;
    }
}
