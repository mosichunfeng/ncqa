package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Join;
import cn.neusoft.xuxiao.mapper.JoinMapper;
import cn.neusoft.xuxiao.service.IJoinService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JoinServiceImpl extends BaseServiceImp<JoinMapper, Join> implements IJoinService {
}
