package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.Kind;
import cn.neusoft.xuxiao.service.IKindService;
import com.magicbeans.base.Pages;
import com.magicbeans.base.ajax.ResponseData;
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

/**
 * 题库标签前端控制器
 */
@Controller
@RequestMapping("/kind")
public class KindController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(KindController.class);

    @Autowired
    private IKindService kindService;

    @RequestMapping("/list")
    public String list(Pages page, Model model){
       page = kindService.findPage(page, null, null);
       model.addAttribute("page", page);
       return "view/kind/list";
    }

    @RequestMapping("/save")
    public String save(Kind kind, RedirectAttributes redirectAttributes){
        if(StringUtils.isEmpty(kind.getId())){
            kindService.save(kind);
        }else{
            kindService.updateSelective(kind);
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
        kindService.delete(id);
        return  result;
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @GetMapping("add")
    public String add(){
        return "view/kind/add";
    }
}
