package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.Student;
import cn.neusoft.xuxiao.entity.WxUser;
import cn.neusoft.xuxiao.service.IClassInfoService;
import cn.neusoft.xuxiao.service.IStudentService;
import cn.neusoft.xuxiao.service.IWxUserService;
import com.magicbeans.base.Pages;
import com.magicbeans.base.ajax.ResponseData;
import com.magicbeans.base.db.Filter;
import com.magicbeans.base.db.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Component
@RequestMapping("/wxuser")
public class WxUserController extends BaseController {

    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassInfoService classInfoService;

    @RequestMapping("/list")
    public String list(Pages page, Model model,String search,String classId){
        List<Filter> filters = new ArrayList();
        List<Filter> filterList = new ArrayList<>();
        if(!StringUtils.isEmpty(search) || !StringUtils.isEmpty(classId)) {
            if (!StringUtils.isEmpty(search)) {
                filters.add(Filter.eq("student_name", search));
            }
            if (!StringUtils.isEmpty(classId)) {
                filters.add(Filter.eq("student_class_id", classId));
            }
            List<Student> studentList = studentService.findList(filters, null);
            String[] ids = new String[studentList.size()];
            for (int i = 0; i < studentList.size(); i++) {
                ids[i] = studentList.get(i).getStudentId();
            }
            filterList.add(Filter.in("student_id", ids));
        }
        page = wxUserService.findPage(page, filterList, null);
        List<WxUser> wxUserList = page.getRecords();
        for (WxUser wxUser : wxUserList) {
            Student student = studentService.findStudentByStudentId(wxUser.getStudentId());
            if(student!=null) {
                wxUser.setStudentClass(student.getStudentClass());
                wxUser.setStudentName(student.getStudentName());
            }
        }

        model.addAttribute("classinfo", classInfoService.findAll());
        model.addAttribute("page", page);
        model.addAttribute("search", search);
        model.addAttribute("classId", classId);
        return "view/wxuser/list";
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("del/{id}")
    public ResponseData del(@PathVariable("id") String id) {
        ResponseData result = new ResponseData();
        wxUserService.delete(id);
        return result;
    }
}
