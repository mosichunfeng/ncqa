package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.QuestionBase;
import cn.neusoft.xuxiao.mapper.QuestionBaseMapper;
import cn.neusoft.xuxiao.service.IQuestionBaseService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionBaseServiceImpl extends BaseServiceImp<QuestionBaseMapper, QuestionBase> implements IQuestionBaseService {

}
