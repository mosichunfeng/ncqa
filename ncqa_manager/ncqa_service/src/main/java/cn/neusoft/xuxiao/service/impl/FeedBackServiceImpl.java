package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.FeedBack;
import cn.neusoft.xuxiao.mapper.FeedBackMapper;
import cn.neusoft.xuxiao.service.IFeedBackService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServiceImpl extends BaseServiceImp<FeedBackMapper, FeedBack> implements IFeedBackService {
}
