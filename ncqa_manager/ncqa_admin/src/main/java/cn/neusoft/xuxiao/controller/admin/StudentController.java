package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.ClassInfo;
import cn.neusoft.xuxiao.entity.Student;
import cn.neusoft.xuxiao.service.IClassInfoService;
import cn.neusoft.xuxiao.service.IStudentService;
import com.github.crab2died.ExcelUtils;
import com.magicbeans.base.Pages;
import com.magicbeans.base.ajax.ResponseData;
import com.magicbeans.base.db.Filter;
import com.magicbeans.base.db.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassInfoService classInfoService;

    @RequestMapping("/list")
    public String list(Pages page, Model model,String studentClassId,String search){
        List<Filter> filters = new ArrayList<>();
        if(!StringUtils.isEmpty(studentClassId)){
            filters.add(Filter.eq("student_class_id", studentClassId));
        }
        if(!StringUtils.isEmpty(search)){
            filters.add(Filter.like("student_name", search));
        }
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("student_id"));
        page = studentService.findPage(page, filters, orders);
        List<Student> studentList = page.getRecords();
        for (Student student : studentList) {
            student.setStudentClass(classInfoService.find(student.getStudentClassId()).getName());
            if((int)((student.getStudentId().charAt(0)))>8000){
                student.setStudentId(student.getStudentId().substring(1, student.getStudentId().length()-1));
            }
            student.setStudentId(student.getStudentId().trim());
        }
        model.addAttribute("classinfo", classInfoService.findAll());
        model.addAttribute("page", page);
        model.addAttribute("studentClassId", studentClassId);
        model.addAttribute("search", search);
        return "view/student/list";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("classinfo", classInfoService.findAll());
        return "view/student/add";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam String id, Model model) {
        Student student = studentService.find(id);
        List<ClassInfo> classinfo = classInfoService.findAll();
        model.addAttribute("student", student);
        model.addAttribute("classinfo", classinfo);
        return "view/student/edit";
    }

    /**
     * 保存信息
     * @param model
     * @return
     */
    @PostMapping("save")
    public String save(Student student, Model model, RedirectAttributes redirectAttributes){
        ClassInfo classInfo = classInfoService.find(student.getStudentClassId());
        student.setStudentClass(classInfo.getName());
        if(StringUtils.isEmpty(student.getId())){
            studentService.save(student);
        }else{
            studentService.updateSelective(student);
        }
        addFlashMessage(redirectAttributes,new Message(Message.Type.success,"保存成功"));
        return redirect("list");
    }

    @RequestMapping("/upload")
    @ResponseBody
    public ResponseData upload(@RequestParam("file") MultipartFile file, String studentClassId) {
        ResponseData responseData = new ResponseData();
        if (file == null) {
            responseData.setStatus(false);
            responseData.setMsg("没检测到文件");
            logger.error("no file!");
            return responseData;
        }
        if(StringUtils.isEmpty(studentClassId)){//导入到指定班级
            ClassInfo classInfo = classInfoService.find(studentClassId);
            if(classInfo==null){
                responseData.setStatus(false);
                responseData.setMsg("班级不存在或已经被删除！");
                logger.error("no class!");
                return responseData;
            }
            try {
                InputStream inputStream = file.getInputStream();
                List<Student> studentList = ExcelUtils.getInstance().readExcel2Objects(inputStream, Student.class
                        , 0, 2147483647, 0);
                for (Student student : studentList) {
                    student.setStudentClassId(studentClassId);
                    student.setStudentClass(classInfo.getName());
                    Student orginStudent = studentService.findStudentByStudentId(student.getStudentId());
                    if(orginStudent == null){
                        studentService.save(student);
                    }
                }
            } catch (Exception e) {
                responseData.setStatus(false);
                responseData.setMsg("导入失败,请检查模板及数据!");
                logger.error("no file!");
                return responseData;
            }

        }else{//导入到所有
            try {
                InputStream is = file.getInputStream();
                List<Student> studentList = ExcelUtils.getInstance().readExcel2Objects(is, Student.class
                        , 0, 2147483647, 0);
                for (Student student : studentList) {
                    List<Filter> filters = new ArrayList<>();
                    filters.add(Filter.eq("name", student.getStudentClass().trim()));
                    List<ClassInfo> list = classInfoService.findList(filters, null);
                    if(list!=null&&list.size()>0){
                        ClassInfo classInfo = list.get(0);
                        student.setStudentClassId(classInfo.getId());
                    }
                    Student orginStudent = studentService.findStudentByStudentId(student.getStudentId());
                    if(orginStudent == null){
                        studentService.save(student);
                    }
                }
            } catch (Exception e) {
                responseData.setStatus(false);
                responseData.setMsg("导入失败,请检查模板及数据!");
                logger.error("no file!");
                return responseData;
            }

        }
        responseData.setStatus(true);
        responseData.setMsg("导入成功!");
        logger.error("no file!");
        return responseData;
    }


    @GetMapping(value = "toLead")
    public String toLead(Model model) {
        model.addAttribute("classinfo", classInfoService.findAll());
        return "view/student/lead";
    }
}
