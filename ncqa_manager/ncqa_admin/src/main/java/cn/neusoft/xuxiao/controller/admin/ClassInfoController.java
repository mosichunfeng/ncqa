package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.ClassInfo;
import cn.neusoft.xuxiao.entity.Student;
import cn.neusoft.xuxiao.service.IClassInfoService;
import cn.neusoft.xuxiao.service.IStudentService;
import com.magicbeans.base.Pages;
import com.magicbeans.base.ajax.ResponseData;
import com.magicbeans.base.db.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/classinfo")
public class ClassInfoController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IClassInfoService classInfoService;

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/list")
    public String list(Pages page, Model model){
       page =  classInfoService.findPage(page, null, null);
        List<ClassInfo> classInfos = page.getRecords();
        for (ClassInfo classInfo : classInfos) {
            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.eq("student_class_id",classInfo.getId()));
            List<Student> list = studentService.findList(filters, null);
            classInfo.setCount(list == null ? 0 : list.size());
        }
        model.addAttribute("page", page);
       return "view/classinfo/list";
    }

    @RequestMapping("/save")
    public String save(ClassInfo classInfo, RedirectAttributes redirectAttributes){
        if(StringUtils.isEmpty(classInfo.getId())){
            classInfoService.save(classInfo);
        }else{
            classInfoService.updateSelective(classInfo);
        }
        addFlashMessage(redirectAttributes, new Message(Message.Type.success, "保存成功"));
        return redirect("list");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("del/{id}")
    public ResponseData del(@PathVariable("id") String id){
        ResponseData result = new ResponseData();
        classInfoService.delete(id);
        return  result;
    }


    /**
     * 跳转到添加页面
     * @return
     */
    @GetMapping("add")
    public String add(){
        return "view/classinfo/add";
    }
}
