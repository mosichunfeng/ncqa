package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Kind;
import cn.neusoft.xuxiao.mapper.KindMapper;
import cn.neusoft.xuxiao.service.IKindService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KindServiceImpl extends BaseServiceImp<KindMapper, Kind> implements IKindService {

}
