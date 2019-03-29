package cn.neusoft.xuxiao.api;

import cn.neusoft.xuxiao.config.FileExecuteRunnable;
import cn.neusoft.xuxiao.config.ServerURLConstants;
import cn.neusoft.xuxiao.entity.Register;
import cn.neusoft.xuxiao.entity.Student;
import cn.neusoft.xuxiao.entity.WxUser;
import cn.neusoft.xuxiao.service.IRegisterService;
import cn.neusoft.xuxiao.service.IStudentService;
import cn.neusoft.xuxiao.service.IWxUserService;
import cn.neusoft.xuxiao.util.CommonUtil;
import cn.neusoft.xuxiao.util.StatusConstant;
import com.alibaba.fastjson.JSONObject;
import com.magicbeans.base.RestBaseController;
import com.magicbeans.base.ajax.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@ResponseBody
@RequestMapping("/register")
public class RegisterController extends RestBaseController {
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IRegisterService registerService;

    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ServerURLConstants serverURLConstants;

    @RequestMapping("/isRegister")
    public ResponseData isRegister(String user_id) {
        if (StringUtils.isEmpty(user_id)) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "用户id不能为空!");
        }
        WxUser wxUser = wxUserService.find(user_id);
        if (wxUser == null) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "用户不存在!");
        }
        Student student = studentService.find("student_id", wxUser.getStudentId() == null ? "" : wxUser.getStudentId());

        JSONObject result = new JSONObject();
        result.put("student_name", student.getStudentName() == null ? "" : student.getStudentName());
        result.put("isRegister", false);
        return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
    }

    @RequestMapping("/register")
    public ResponseData register(MultipartFile file, String user_id, String address) {
        if (CommonUtil.isEmpty(file, user_id, address)) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "参数不完整!");
        }
        String fileName = UUID.randomUUID().toString()+".jpg";
        String path = "";
        WxUser wxUser = wxUserService.find(user_id);
        Student student = studentService.find("student_id", wxUser.getStudentId());

        Register register = new Register();
        register.setStudentId(student.getId());
        register.setStudentName(student.getStudentName());
        register.setStartTime(new Date().getTime());
        register.setAddress(address);
        if(student.getWorkDetail() == 1) {//实习学生
            path = serverURLConstants.getOutsideImageUrl()+fileName;
            register.setImageUrl(path);
            register.setStatus(1);
            registerService.save(register);
        }else{//校内学生
            path = serverURLConstants.getInsideImageUrl()+fileName;
            register.setImageUrl(path);
            register.setStatus(0);
            registerService.save(register);
        }
        //开启异步任务存储文件
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(new FileExecuteRunnable(file,path));
        logger.info("学号:{0},姓名:${1},时间${2}签到成功!",student.getStudentId(),student.getStudentName(),new Date().toString());

        JSONObject result = new JSONObject();
        result.put("student_name", student.getStudentName());
        result.put("start_time", new Date());
        result.put("isRegister", true);
        return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
    }

}
