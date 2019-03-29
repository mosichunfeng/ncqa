package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Question;
import cn.neusoft.xuxiao.entity.RightAnswer;
import cn.neusoft.xuxiao.mapper.QuestionMapper;
import cn.neusoft.xuxiao.service.IQuestionService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuestionServiceImpl extends BaseServiceImp<QuestionMapper, Question> implements IQuestionService {

    @Override
    public Map<String,String> findFillAnswerRefByBaseIdAndType(String questionBaseId) {
        return mapper.findFillAnswerRefByBaseIdAndType(questionBaseId);
    }

    @Override
    public Map<String,String> findSelectAnswerRefByBaseIdAndType(String questionBaseId) {
        return mapper.findSelectAnswerRefByBaseIdAndType(questionBaseId);
    }
}
