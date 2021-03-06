package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.Admin;
import cn.neusoft.xuxiao.entity.Call;
import cn.neusoft.xuxiao.entity.CallRecord;
import cn.neusoft.xuxiao.service.*;
import cn.neusoft.xuxiao.util.TimeTool;
import com.magicbeans.base.Pages;
import com.magicbeans.base.db.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/call")
public class CallController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private ICallService callService;

    @Autowired
    private IClassInfoService classInfoService;

    @Autowired
    private ICallRecordService callRecordService;

    @Autowired
    private IRegisterService registerService;

    @Autowired
    private IAdminService adminService;

    @RequestMapping("/list")
    public String list(Pages page, Model model) {
        List<Filter> filters = new ArrayList();
        page = callService.findPage(page, filters, null);
        List<Call> callList = page.getRecords();
        for (Call call : callList) {
            List<Filter> filters1 = new ArrayList<>();
            filters1.add(Filter.eq("call_id", call.getId()));
            call.setCallRecordList(callRecordService.findList(filters1, null));
        }
        model.addAttribute("page", page);
        return "/view/call/list";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("classinfo", classInfoService.findAll());
        return "view/call/add";
    }

    @RequestMapping("/save")
    public String save(Call call, String classes, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(classes)) {
            addFlashMessage(redirectAttributes, new Message(Message.Type.error, "未选择班级!"));
            return redirect("list");
        }
        call.setStartTime(System.currentTimeMillis());
        String[] split = classes.split(",");
        callService.saveCallAndRecord(call, classes);
        addFlashMessage(redirectAttributes, new Message(Message.Type.success, "保存成功!"));
        return redirect("list");
    }

    @RequestMapping("/registerList")
    public String registerList(Pages page, Model model, String callId) {
        List<Filter> filters1 = new ArrayList<>();

        filters1.add(Filter.eq("call_id", callId));
        List<CallRecord> callList = callRecordService.findList(filters1, null);
        List<Filter> filters = new ArrayList<>();
        if (callList != null && callList.size() > 0) {
            Call call = callService.find(callId);

            String[] ids = new String[callList.size()];
            for (int i = 0; i < callList.size(); i++) {
                ids[i] = callList.get(i).getClassId();
            }
            filters.add(Filter.in("student_class_id", ids));
            filters.add(Filter.eq("status", 1));
            filters.add(Filter.between("start_time", call.getStartTime(), call.getEndTime()));
        }
        page = registerService.findPage(page, filters, null);
        model.addAttribute("page", page);
        model.addAttribute("classinfo", classInfoService.findAll());
        return "view/call/register";
    }


    @RequestMapping("/photo")
    public String photo(Pages page,String callId,String studentClassId,Model model){
        List<Filter> filters1 = new ArrayList<>();

        filters1.add(Filter.eq("call_id", callId));
        List<CallRecord> callList = callRecordService.findList(filters1, null);
        List<Filter> filters = new ArrayList<>();
        if (callList != null && callList.size() > 0) {
            Call call = callService.find(callId);
            if(StringUtils.isEmpty(studentClassId)) {
                String[] ids = new String[callList.size()];
                for (int i = 0; i < callList.size(); i++) {
                    ids[i] = callList.get(i).getClassId();
                }
                filters.add(Filter.in("student_class_id", ids));
            }else{
                filters.add(Filter.eq("student_class_id", studentClassId));
            }
            filters.add(Filter.eq("status", 1));
            filters.add(Filter.between("start_time", call.getStartTime(), call.getEndTime()));
        }
        page = registerService.findPage(page, filters, null);
        model.addAttribute("classinfo", classInfoService.findAll());
        model.addAttribute("studentClassId", studentClassId);
        model.addAttribute("page", page);
        return "view/call/photo";
    }

    @RequestMapping("/auto")
    public String auto(HttpServletRequest request){
        Admin admin = getLoginUser();

        return null;
    }
}
