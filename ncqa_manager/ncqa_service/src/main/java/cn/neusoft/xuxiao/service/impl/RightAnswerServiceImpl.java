package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.RightAnswer;
import cn.neusoft.xuxiao.mapper.RightAnswerMapper;
import cn.neusoft.xuxiao.service.IRightAnswerService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RightAnswerServiceImpl extends BaseServiceImp<RightAnswerMapper,RightAnswer> implements IRightAnswerService {
}
