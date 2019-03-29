package cn.neusoft.xuxiao.service;

import cn.neusoft.xuxiao.entity.Question;
import com.magicbeans.base.BaseService;

import java.util.Map;


public interface IQuestionService extends BaseService<Question> {
    Map<String,String> findFillAnswerRefByBaseIdAndType(String questionBaseId);
    Map<String,String> findSelectAnswerRefByBaseIdAndType(String questionBaseId);
}
