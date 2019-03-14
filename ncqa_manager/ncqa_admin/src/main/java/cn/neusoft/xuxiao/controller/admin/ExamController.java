package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.ExamHistory;
import cn.neusoft.xuxiao.entity.Student;
import cn.neusoft.xuxiao.service.IExamHistoryService;
import cn.neusoft.xuxiao.service.IQuestionBaseService;
import cn.neusoft.xuxiao.service.IStudentService;
import cn.neusoft.xuxiao.util.TimeTool;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ExamHistory.class);

    @Autowired
    private IExamHistoryService examHistoryService;

    @Autowired
    private IQuestionBaseService questionBaseService;

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/list")
    public String list(Pages page, Model model, String questionBaseId) {
        List<Filter> filters = new ArrayList<>();
        if (!StringUtils.isEmpty(questionBaseId)) {
            filters.add(Filter.eq("question_base_id", questionBaseId));
        }
        page = examHistoryService.findPage(page, filters, null);
        List<ExamHistory> examHistoryList = page.getRecords();
        iterExamHistory(examHistoryList);
        model.addAttribute("page", page);
        model.addAttribute("questionbase", questionBaseService.findAll());
        model.addAttribute("questionBaseId", questionBaseId);
        return "view/exam/list";
    }

    @RequestMapping("/export")
    public void export(String questionBaseId, HttpServletResponse response) {
        List<Filter> filters = new ArrayList<>();
        if (!StringUtils.isEmpty(questionBaseId)) {
            filters.add(Filter.eq("question_base_id", questionBaseId));
        }
        List<ExamHistory> list = examHistoryService.findList(filters, null);
        iterExamHistory2(list);

        try {
            String fileName = "用户成绩表.xlsx";
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
            ExcelUtils.getInstance().exportObjects2Excel(list, ExamHistory.class, true, os);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void iterExamHistory(List<ExamHistory> list) {
        for (ExamHistory examHistory : list) {
            Student student = studentService.findStudentByUid(examHistory.getUserId());
            if (student != null) {
                examHistory.setStudentName(student.getStudentName() == null ? "" : student.getStudentName());
                examHistory.setStudentClass(student.getStudentClass());
            }
            examHistory.setQuestionBaseName(questionBaseService.find(examHistory.getQuestionBaseId()).getName());
        }
    }

    private void iterExamHistory2(List<ExamHistory> list) {
        for (ExamHistory examHistory : list) {
            Student student = studentService.findStudentByUid(examHistory.getUserId());
            if (student != null) {
                examHistory.setStudentName(student.getStudentName() == null ? "" : student.getStudentName());
                examHistory.setStudentClass(student.getStudentClass());
            }
            examHistory.setQuestionBaseName(questionBaseService.find(examHistory.getQuestionBaseId()).getName());
            examHistory.setStart(TimeTool.DateToString(new Date(examHistory.getStartTime())));
            examHistory.setEnd(examHistory.getEndTime() == null ? "" : TimeTool.DateToString(new Date(examHistory.getEndTime())));
            examHistory.setFinished(examHistory.getStatus() == 0 ? "进行中" : "已完成");
        }
    }
}


