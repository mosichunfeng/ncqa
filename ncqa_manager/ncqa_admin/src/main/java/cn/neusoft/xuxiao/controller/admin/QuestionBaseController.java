package cn.neusoft.xuxiao.controller.admin;


import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.VO.FillQuestionDO;
import cn.neusoft.xuxiao.VO.SelectQuestionDO;
import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.*;
import cn.neusoft.xuxiao.service.*;
import cn.neusoft.xuxiao.util.StatusConstant;
import com.alibaba.druid.util.StringUtils;
import com.github.crab2died.ExcelUtils;
import com.magicbeans.base.Pages;
import com.magicbeans.base.ajax.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/questionbase")
public class QuestionBaseController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QuestionBaseController.class);

    @Autowired
    private IQuestionBaseService questionBaseService;

    @Autowired
    private IKindService kindService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IAnswerService answerService;

    @Autowired
    private IRightAnswerService rightAnswerService;

    @RequestMapping("/list")
    public String list(Pages page, Model model) {
        page = questionBaseService.findPage(page, null, null);
        List<QuestionBase> records = page.getRecords();
        for (QuestionBase record : records) {
            record.setKindName(kindService.find(record.getKindId()).getName());
        }
        model.addAttribute("page", page);
        return "view/questionbase/list";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam String id, Model model) {
        QuestionBase questionBase = questionBaseService.find(id);
        List<Kind> kindList = kindService.findAll();
        model.addAttribute("questionbase", questionBaseService.find(id));
        model.addAttribute("kind", kindList);
        System.out.println(questionBase);
        return "view/questionbase/edit";
    }

    @RequestMapping("/save")
    public String save(QuestionBase questionBase, RedirectAttributes redirectAttributes, Model model) {
        if (StringUtils.isEmpty(questionBase.getId())) {
            questionBaseService.save(questionBase);
        } else {
            questionBaseService.updateSelective(questionBase);
        }
        addFlashMessage(redirectAttributes, new Message(Message.Type.success, "保存成功"));
        return redirect("list");
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
        questionBaseService.delete(id);
        return result;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("kind", kindService.findAll());
        return "view/questionbase/add";
    }

    @GetMapping(value = "toLead")
    public String toLead(Model model) {
        model.addAttribute("questionbase", questionBaseService.findAll());
        return "view/questionbase/lead";
    }


    /**
     * 导入excel
     */
    @RequestMapping("upload")
    @ResponseBody
    public ResponseData upload(@RequestParam("file") MultipartFile file, String questionBaseId) {
        ResponseData responseData = new ResponseData();
        QuestionBase questionBase = questionBaseService.find(questionBaseId);
        if (StudentController.noFile(file, responseData, logger)) return responseData;
        if (questionBase == null) {
            responseData.setStatus(false);
            responseData.setMsg("该题库不存在或者已被删除！");
            logger.error("base not exist!");
            return responseData;
        }
        try {
            InputStream inputStream = file.getInputStream();
            List<SelectQuestionDO> selectQuestionList = ExcelUtils.getInstance().readExcel2Objects(inputStream, SelectQuestionDO.class
                    , 0, 2147483647, 0);
            for (SelectQuestionDO select : selectQuestionList) {
                Question question = new Question();
                question.setQuestionBaseId(questionBase.getId());
                question.setQuestionType(1);
                question.setSelectType(select.getSelectType());
                question.setContent(select.getContent());
                question.setGrade(select.getGrade());

                Boolean save = questionService.save(question);


                Answer answer1 = new Answer();
                answer1.setAnswerIndex("选项A");
                answer1.setQuestionId(question.getId());
                answer1.setAnswerContent(select.getAnswerA());
                answer1.setAnswerType(1);
                answerService.save(answer1);


                Answer answer2 = new Answer();
                answer2.setAnswerIndex("选项B");
                answer2.setQuestionId(question.getId());
                answer2.setAnswerContent(select.getAnswerB());
                answer2.setAnswerType(1);
                answerService.save(answer2);

                Answer answer3 = new Answer();
                answer3.setAnswerIndex("选项C");
                answer3.setAnswerContent(select.getAnswerC());
                answer3.setQuestionId(question.getId());
                answer3.setAnswerType(1);
                answerService.save(answer3);

                Answer answer4 = new Answer();
                answer4.setAnswerIndex("选项D");
                answer4.setQuestionId(question.getId());
                answer4.setAnswerContent(select.getAnswerD());
                answer4.setAnswerType(1);
                answerService.save(answer4);

                RightAnswer rightAnswer = new RightAnswer();
                rightAnswer.setAnswerType(1);
                rightAnswer.setQuestionId(question.getId());
                rightAnswer.setRightAnswerIndex(select.getRightAnswer());
                rightAnswerService.save(rightAnswer);

            }
            List<FillQuestionDO> fillQuestionList = ExcelUtils.getInstance().readExcel2Objects(file.getInputStream(), FillQuestionDO.class
                    , 0, 2147483647, 1);
            for (FillQuestionDO fill : fillQuestionList) {
                Question question1 = new Question();
                question1.setQuestionType(2);
                question1.setQuestionBaseId(questionBase.getId());
                question1.setContent(fill.getContent());
                question1.setGrade(fill.getGrade());
                questionService.save(question1);

                RightAnswer rightAnswer1 = new RightAnswer();
                rightAnswer1.setQuestionId(question1.getId());
                rightAnswer1.setRightAnswerContent(fill.getRightAnswer());
                rightAnswer1.setAnswerType(2);
                rightAnswerService.save(rightAnswer1);
            }
            responseData.setMsg("导入成功");
            responseData.setStatus(true);
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setStatus(false);
            responseData.setMsg("导入失败,检查模板及数据");
            logger.error("check your template!");
            return responseData;
        }
    }
}
