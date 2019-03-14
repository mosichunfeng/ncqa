package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Question;
import cn.neusoft.xuxiao.mapper.QuestionMapper;
import cn.neusoft.xuxiao.service.IQuestionService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionServiceImpl extends BaseServiceImp<QuestionMapper, Question> implements IQuestionService {
}
