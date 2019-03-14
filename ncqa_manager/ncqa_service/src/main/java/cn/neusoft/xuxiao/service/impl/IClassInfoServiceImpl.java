package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.ClassInfo;
import cn.neusoft.xuxiao.mapper.ClassInfoMapper;
import cn.neusoft.xuxiao.service.IClassInfoService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IClassInfoServiceImpl extends BaseServiceImp<ClassInfoMapper,ClassInfo> implements IClassInfoService {
}
