package cn.neusoft.xuxiao.api;

import cn.neusoft.xuxiao.api.entity.SubmitContentRequest;
import cn.neusoft.xuxiao.entity.*;
import cn.neusoft.xuxiao.service.*;
import cn.neusoft.xuxiao.util.*;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.magicbeans.base.RestBaseController;
import com.magicbeans.base.ajax.ResponseData;
import com.magicbeans.base.db.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController extends RestBaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IQuestionBaseService questionBaseService;

    @Autowired
    private IActivityCodeService activityCodeService;

    @Autowired
    private IExamHistoryService examHistoryService;

    @RequestMapping("/getSessionKeyAndOropenid")
    public ResponseData getSessionKeyAndOropenid(String code) {
        if (StringUtils.isEmpty(code)) {
            return buildFailureJson(StatusConstant.Fail_CODE, "未查到资源地址");
        }
        String[] ret = WxApplicationDecoder.parse(code);
        if (StringUtils.isEmpty(ret[0])) {
            return buildFailureJson(StatusConstant.Fail_CODE, "Code码非法或被禁用");
        }
        String openId = Base64Utils.encoder(ret[0]);
        WxUser user = wxUserService.find("open_id", openId);
        String userId = "";
        boolean isBind = false;
        if (null == user) {
            WxUser wxUser = new WxUser();
            wxUser.setOpenId(openId);
            wxUserService.save(wxUser);
            userId = wxUser.getId();
        } else {
            userId = user.getId();
            isBind = true;
        }

        JSONObject result = new JSONObject();
        result.put("bind_info", isBind);
        result.put("user_id", userId);
        return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
    }

    @RequestMapping("/bindStudentInfo")
    public ResponseData bindStudentInfo(String id, String student_id, String student_name) {
        if (CommonUtil.isEmpty(id, student_id, student_name)) {
            return buildFailureJson(StatusConstant.Fail_CODE, "参数填写不完整!");
        }
        WxUser wxUser = wxUserService.find("student_id", student_id);
        if (null != wxUser) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "该学生已经绑定信息!");
        }
        Student student = studentService.find("student_id", student_id);
        if (student == null) {
            return buildFailureJson(410, "学号输入错误!");
        }
        if (!student_name.equals(student.getStudentName())) {
            return buildFailureJson(409, "学号姓名不匹配!");
        }
        WxUser user = wxUserService.find(id);
        if (user == null) {
            logger.error("有人入侵");
            return buildFailureJson(444, "滚出克!!!");
        }
        user.setStudentId(student_id);
        wxUserService.updateSelective(user);

        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("student_id", student_id);
        result.put("student_name", student.getStudentName());
        result.put("student_gender", student.getStudentGender());
        return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
    }

    @RequestMapping("/getUserInfoAndBase")
    public ResponseData getUserInfoAndBase(String id) {
        if (StringUtils.isEmpty(id)) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "id不能为空!");
        }
        WxUser wxUser = wxUserService.find(id);
        Student student = studentService.find("student_id", wxUser.getStudentId());
        if (student == null) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "无此学生!");
        }

        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.gt("start_time", new Date()));
        filters.add(Filter.lt("end_time", new Date()));
        List<QuestionBase> list = questionBaseService.findList(filters, null);

        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("student_id", student.getStudentId());
        result.put("student_name", student.getStudentName());
        result.put("base", list);
        return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
    }

    @RequestMapping("/ensureJoin")
    public ResponseData ensureJoin(String user_id, String base_id) {
        if (CommonUtil.isEmpty(user_id, base_id)) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "参数填写不完整!");
        }
        QuestionBase base = questionBaseService.find(base_id);
        if (base == null) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "活动不存在或者已经被删除!");
        }
        if (new Date(base.getEndTime()).getTime() < new Date().getTime()) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "活动已经结束，无法报名!");
        }

        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("question_base_id", base_id));
        filters.add(Filter.eq("user_id", user_id));
        List<ActivityCode> list = activityCodeService.findList(filters, null);
        String code = "";
        boolean isJoined = false;
        if (list != null && list.size() > 0) {
            ActivityCode activityCode = list.get(0);
            if (activityCode != null) {
                code = activityCode.getCode();
                isJoined = true;
            }
        } else {
            ActivityCode activityCode = new ActivityCode();
            activityCode.setUserId(user_id);
            activityCode.setQuestionBaseId(base_id);
            do {
                code = RandomUtil.randomStr();
            } while (activityCodeService.find("code", code) != null);
            activityCodeService.save(activityCode);
        }
        JSONObject result = new JSONObject();
        result.put("isJoined", isJoined);
        result.put("activity_code", code);
        return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
    }

    @RequestMapping("/startAnswerQuestion")
    public ResponseData startAnswerQuestion(String user_id,String code){
        if (CommonUtil.isEmpty(user_id, code)) {
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "参数填写不完整!");
        }
        ActivityCode activityCode = activityCodeService.find("code", code);
        if(activityCode==null || !activityCode.getUserId().equals(user_id)){
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "答题码错误!");
        }
        JSONObject result = new JSONObject();
        ExamHistory exam = examHistoryService.find("code", code);
        if(exam!=null){
            if(exam.getStatus() == 1){//已完成，返回结果
                result.put("user_id", user_id);
                result.put("status", 1);
                result.put("grade",exam.getGrade());
                return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
            }else{
                long minutes = (new Date().getTime() - exam.getStartTime()) / 60000;
                long last_minutes = 120 - minutes;
                result.put("user_id", user_id);
                result.put("status", 0);
                result.put("last_minutes", last_minutes);
                QuestionBase questionBase = questionBaseService.findAllQuestionAndAnswer(activityCode.getQuestionBaseId());
                result.put("question_base", questionBase);
            }
        }else{
            result.put("user_id", user_id);
            result.put("status", 0);
            result.put("last_minutes", StatusConstant.ALL_EXAM_TIME);
        }
        return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", result);
    }


    @RequestMapping("/submitContent")
    public ResponseData submitContent(SubmitContentRequest reqMsg){
        if(StringUtils.isEmpty(reqMsg.getUser_id())){
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "用户id不能为空!");
        }
        if(StringUtils.isEmpty(reqMsg.getQuestion_base_id())){
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "题库id不能为空!");
        }

        JSONObject result = new JSONObject();
        String []value = {reqMsg.getUser_id(),reqMsg.getQuestion_base_id()};
        String []key = {"user_id","question_base_id"};
        ExamHistory exam = examHistoryService.find(key, value);
        if(exam == null){
            logger.error("有人入侵!");
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "信不信爷顺着网线来打你？给爷爬!");
        }

        //计算答题时间
        if((new Date().getTime() - exam.getStartTime()) > StatusConstant.ALL_EXAM_TIME){
            result.put("grade", result);
        }


        return null;
    }
}
