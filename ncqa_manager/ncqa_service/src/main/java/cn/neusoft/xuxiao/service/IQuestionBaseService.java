package cn.neusoft.xuxiao.service;

import cn.neusoft.xuxiao.entity.Question;
import cn.neusoft.xuxiao.entity.QuestionBase;
import com.magicbeans.base.BaseService;

public interface IQuestionBaseService extends BaseService<QuestionBase> {
    QuestionBase findAllQuestionAndAnswer(String questionBaseId);
}
