package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Answer;
import cn.neusoft.xuxiao.mapper.AnswerMapper;
import cn.neusoft.xuxiao.service.IAnswerService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IAnswerServiceImpl extends BaseServiceImp<AnswerMapper, Answer> implements IAnswerService {
}
