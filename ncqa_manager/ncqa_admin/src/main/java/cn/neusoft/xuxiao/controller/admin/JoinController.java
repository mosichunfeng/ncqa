package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.*;
import cn.neusoft.xuxiao.service.*;
import com.github.crab2died.ExcelUtils;
import com.magicbeans.base.Pages;
import com.magicbeans.base.db.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/join")
public class JoinController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(JoinController.class);

    @Autowired
    private IJoinService joinService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IQuestionBaseService questionBaseService;

    @Autowired
    private IExamHistoryService examHistoryService;

    @Autowired
    private IClassInfoService classInfoService;

    @RequestMapping("/list")
    public String list(Pages page, Model model,String questionBaseId){
        List<Filter> filterList = new ArrayList<>();
        if(!StringUtils.isEmpty(questionBaseId)){
            filterList.add(Filter.eq("question_base_id", questionBaseId));
        }

        page = joinService.findPage(page, filterList, null);
        List<Join> joinList = page.getRecords();
        for (Join join : joinList) {
            Student student = studentService.findStudentByUid(join.getUserId());
            if(student!=null) {
                join.setStudentName(student.getStudentName() == null ? "" : student.getStudentName());
                join.setStudentClass(student.getStudentClass());
            }
            join.setQuestionBaseName(questionBaseService.find(join.getQuestionBaseId()).getName());
            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.eq("question_base_id", join.getQuestionBaseId()));
            filters.add(Filter.eq("user_id", join.getUserId()));
            List<ExamHistory> list = examHistoryService.findList(filters, null);
            if(list == null || list.size()==0){
                join.setFinished(0);
            }else{
                join.setFinished(1);
            }
        }
        model.addAttribute("classinfo", classInfoService.findAll());
        model.addAttribute("questionbase", questionBaseService.findAll());
        model.addAttribute("questionBaseId", questionBaseId);
        model.addAttribute("page", page);
        return "view/join/list";
    }

    @RequestMapping("/export")
    public void export(String questionBaseId, HttpServletResponse response){
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("question_base_id", questionBaseId));
        List<Join> list = joinService.findList(filters, null);
        QuestionBase base = questionBaseService.find(questionBaseId);
        try {
            String fileName = base.getName()+"报名表.xlsx";
            fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");

            // 设置强制下载不打开
            response.setContentType("application/octet-stream");
            // 设置文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            //设置请求头
            response.setHeader("content-type", "application/octet-stream");
            //设置字符集编码
            response.setCharacterEncoding("utf-8");

            OutputStream os = response.getOutputStream();
            ExcelUtils.getInstance().exportObjects2Excel(list, Join.class, true, os);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
