package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.User;
import cn.neusoft.xuxiao.mapper.UserMapper;
import cn.neusoft.xuxiao.service.IUserService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImp<UserMapper, User> implements IUserService {
}
