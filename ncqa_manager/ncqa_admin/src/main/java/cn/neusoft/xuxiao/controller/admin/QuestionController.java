package cn.neusoft.xuxiao.controller.admin;

import cn.neusoft.xuxiao.Message;
import cn.neusoft.xuxiao.VO.FillQuestionDO;
import cn.neusoft.xuxiao.VO.SelectQuestionDO;
import cn.neusoft.xuxiao.controller.BaseController;
import cn.neusoft.xuxiao.entity.Answer;
import cn.neusoft.xuxiao.entity.Question;
import cn.neusoft.xuxiao.entity.QuestionBase;
import cn.neusoft.xuxiao.entity.RightAnswer;
import cn.neusoft.xuxiao.service.IAnswerService;
import cn.neusoft.xuxiao.service.IQuestionBaseService;
import cn.neusoft.xuxiao.service.IQuestionService;
import cn.neusoft.xuxiao.service.IRightAnswerService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IQuestionBaseService questionBaseService;

    @Autowired
    private IAnswerService answerService;

    @Autowired
    private IRightAnswerService rightAnswerService;

    @RequestMapping("/select/list")
    public String selectList(Pages page, Model model, String questionBaseId) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("question_type", 1));
        if (!StringUtils.isEmpty(questionBaseId)) {
            filters.add(Filter.eq("question_base_id", questionBaseId));
        }
        page = questionService.findPage(page, filters, null);
        List<Question> questionList = page.getRecords();
        iterList(questionList, 0);
        List<QuestionBase> base = questionBaseService.findAll();
        model.addAttribute("base1", base);
        model.addAttribute("page", page);
        model.addAttribute("questionBaseId", questionBaseId);
        return "view/question/select/list";
    }

    @RequestMapping("/fill/list")
    public String fillList(Pages page, Model model, String questionBaseId) {
        List<Filter> filters = new ArrayList<>();
        if (!StringUtils.isEmpty(questionBaseId)) {
            filters.add(Filter.eq("question_base_id", questionBaseId));
        }
        filters.add(Filter.eq("question_type", 2));
        page = questionService.findPage(page, filters, null);
        List<Question> questionList = page.getRecords();
        iterList(questionList, 1);
        List<QuestionBase> base = questionBaseService.findAll();
        model.addAttribute("base1", base);
        model.addAttribute("page", page);
        model.addAttribute("questionBaseId", questionBaseId);
        return "view/question/fill/list";
    }

    private void iterList(List<Question> questionList, int flag) {
        for (Question question : questionList) {
            List<Filter> filters1 = new ArrayList<>();
            filters1.add(Filter.eq("question_id", question.getId()));
            question.setAnswerList(answerService.findList(filters1, null));
            QuestionBase questionBase = questionBaseService.find(question.getQuestionBaseId());
            if (questionBase != null) {
                question.setQuestionBaseName(questionBase.getName() == null ? "" : questionBase.getName());
            }
            if (flag == 0) {
                question.setRightAnswer(rightAnswerService.findList(filters1, null).get(0).getRightAnswerIndex());
            } else {
                question.setRightAnswer(rightAnswerService.findList(filters1, null).get(0).getRightAnswerContent());
            }
        }
    }

    /**
     * 填空题编辑
     */
    @RequestMapping("/fill/edit")
    public String fillEdit(String questionId, Model model) {
        Question question1 = questionService.find(questionId);
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("question_id", question1.getId()));
        List<RightAnswer> list = rightAnswerService.findList(filters, null);
        FillQuestionDO question = new FillQuestionDO();
        question.setContent(question1.getContent());
        question.setGrade(question1.getGrade());
        question.setQuestionBaseId(question1.getQuestionBaseId());
        question.setId(question1.getId());
        question.setRightAnswer(list.get(0).getRightAnswerContent());
        model.addAttribute("question", question);
        return "view/question/fill/edit";
    }

    /**
     * 选择题编辑
     */
    @RequestMapping("/select/edit")
    public String selectEdit(String questionId, Model model) {
        Question question1 = questionService.find(questionId);
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("question_id", question1.getId()));
        List<Answer> list = answerService.findList(filters, null);
        List<RightAnswer> list1 = rightAnswerService.findList(filters, null);
        SelectQuestionDO question = new SelectQuestionDO();
        question.setRightAnswer(list1.get(0).getRightAnswerIndex());
        question.setId(question1.getId());
        question.setContent(question1.getContent());
        question.setQuestionBaseId(question1.getQuestionBaseId());
        for (Answer answer : list) {
            if (answer.getAnswerIndex().equals("选项A")) {
                question.setAnswerA(answer.getAnswerContent());
            } else if (answer.getAnswerIndex().equals("选项B")) {
                question.setAnswerB(answer.getAnswerContent());
            } else if (answer.getAnswerIndex().equals("选项C")) {
                question.setAnswerC(answer.getAnswerContent());
            } else if (answer.getAnswerIndex().equals("选项D")) {
                question.setAnswerD(answer.getAnswerContent());
            }
        }
        model.addAttribute("question", question);
        return "view/question/select/edit";
    }

    @RequestMapping("/select/save")
    public String selectSave(SelectQuestionDO selectQuestionDO, RedirectAttributes redirectAttributes, Model model) {
        if (StringUtils.isEmpty(selectQuestionDO.getId())) {
            Question question = new Question();
            question.setContent(selectQuestionDO.getContent());
            question.setQuestionType(1);
            question.setSelectType(selectQuestionDO.getSelectType());
            question.setQuestionBaseId(selectQuestionDO.getQuestionBaseId());
            question.setGrade(selectQuestionDO.getGrade());
            questionService.save(question);

            Answer answer1 = new Answer();
            answer1.setQuestionId(question.getId());
            answer1.setAnswerType(1);
            answer1.setAnswerIndex("选项A");
            answer1.setAnswerContent(selectQuestionDO.getAnswerA());

            Answer answer2 = new Answer();
            answer2.setQuestionId(question.getId());
            answer2.setAnswerType(1);
            answer2.setAnswerIndex("选项B");
            answer2.setAnswerContent(selectQuestionDO.getAnswerB());

            Answer answer3 = new Answer();
            answer3.setQuestionId(question.getId());
            answer3.setAnswerType(1);
            answer3.setAnswerIndex("选项C");
            answer3.setAnswerContent(selectQuestionDO.getAnswerC());

            Answer answer4 = new Answer();
            answer4.setQuestionId(question.getId());
            answer4.setAnswerType(1);
            answer4.setAnswerIndex("选项D");
            answer4.setAnswerContent(selectQuestionDO.getAnswerD());

            answerService.save(answer1);
            answerService.save(answer2);
            answerService.save(answer3);
            answerService.save(answer4);

            RightAnswer rightAnswer = new RightAnswer();
            rightAnswer.setAnswerType(1);
            rightAnswer.setQuestionId(question.getId());
            rightAnswer.setRightAnswerIndex(selectQuestionDO.getRightAnswer());
            rightAnswerService.save(rightAnswer);
        } else {
            Question question = new Question();
            question.setId(selectQuestionDO.getId());
            question.setContent(selectQuestionDO.getContent());
            question.setQuestionType(1);
            question.setGrade(selectQuestionDO.getGrade());
            question.setSelectType(selectQuestionDO.getSelectType());
            questionService.updateSelective(question);
            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.eq("question_id", question.getId()));
            List<Answer> list = answerService.findList(filters, null);
            for (Answer answer : list) {
                if(answer.getAnswerIndex().equals("选项A")) {
                    answer.setAnswerContent(selectQuestionDO.getAnswerA());
                    answer.setAnswerType(1);
                    answerService.updateSelective(answer);
                }else if(answer.getAnswerIndex().equals("选项B")){
                    answer.setAnswerContent(selectQuestionDO.getAnswerB());
                    answer.setAnswerType(1);
                    answerService.updateSelective(answer);
                }else if(answer.getAnswerIndex().equals("选项C")){
                    answer.setAnswerContent(selectQuestionDO.getAnswerC());
                    answer.setAnswerType(1);
                    answerService.updateSelective(answer);
                }else if(answer.getAnswerIndex().equals("选项D")){
                    answer.setAnswerContent(selectQuestionDO.getAnswerD());
                    answer.setAnswerType(1);
                    answerService.updateSelective(answer);
                }
                List<RightAnswer> list1 = rightAnswerService.findList(filters,null);
                RightAnswer rightAnswer = list1.get(0);
                rightAnswer.setRightAnswerIndex(selectQuestionDO.getRightAnswer());
                rightAnswerService.updateSelective(rightAnswer);
            }
        }
        addFlashMessage(redirectAttributes, new Message(Message.Type.success, "保存成功"));
        return redirect("list");
    }

    @RequestMapping("/fill/save")
    public String fillSave(FillQuestionDO fillQuestionDO, RedirectAttributes redirectAttributes, Model model) {
        if (StringUtils.isEmpty(fillQuestionDO.getId())) {
            Question question = new Question();
            question.setQuestionBaseId(fillQuestionDO.getQuestionBaseId());
            question.setQuestionType(2);
            question.setContent(fillQuestionDO.getContent());
            question.setGrade(fillQuestionDO.getGrade());
            questionService.save(question);

            RightAnswer rightAnswer = new RightAnswer();
            rightAnswer.setAnswerType(2);
            rightAnswer.setQuestionId(question.getId());
            rightAnswer.setRightAnswerContent(fillQuestionDO.getRightAnswer());
            rightAnswerService.save(rightAnswer);
        } else {
            Question question = new Question();
            question.setId(fillQuestionDO.getId());
            question.setQuestionBaseId(fillQuestionDO.getQuestionBaseId());
            question.setQuestionType(2);
            question.setContent(fillQuestionDO.getContent());
            question.setGrade(fillQuestionDO.getGrade());
            questionService.updateSelective(question);

            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.eq("question_id", question.getId()));
            List<RightAnswer> list = rightAnswerService.findList(filters, null);
            RightAnswer rightAnswer1 = list.get(0);
            rightAnswer1.setRightAnswerContent(fillQuestionDO.getRightAnswer());
            rightAnswerService.updateSelective(rightAnswer1);
        }

        addFlashMessage(redirectAttributes, new Message(Message.Type.success, "保存成功"));
        return redirect("list");
    }
    /**
     * 跳转到添加页面
     *
     * @return
     */
    @GetMapping("/select/add")
    public String selectAdd(Model model) {
        model.addAttribute("questionbase", questionBaseService.findAll());
        return "view/question/select/add";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @GetMapping("/fill/add")
    public String fillAdd(Model model) {
        model.addAttribute("questionbase", questionBaseService.findAll());
        return "view/question/fill/add";
    }
}
