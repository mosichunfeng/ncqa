package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.WxUser;
import cn.neusoft.xuxiao.mapper.WxUserMapper;
import cn.neusoft.xuxiao.service.IWxUserService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl extends BaseServiceImp<WxUserMapper, WxUser> implements IWxUserService {
}
