package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Register;
import cn.neusoft.xuxiao.mapper.RegisterMapper;
import cn.neusoft.xuxiao.service.IRegisterService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends BaseServiceImp<RegisterMapper, Register> implements IRegisterService {
}
