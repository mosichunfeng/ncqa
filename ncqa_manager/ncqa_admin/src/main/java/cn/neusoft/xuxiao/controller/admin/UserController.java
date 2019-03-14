package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IUserService userService;


}
