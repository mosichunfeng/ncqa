package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.CallRecord;
import cn.neusoft.xuxiao.mapper.CallRecordMapper;
import cn.neusoft.xuxiao.service.ICallRecordService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;

@Service
public class CallRecordServiceImpl extends BaseServiceImp<CallRecordMapper, CallRecord> implements ICallRecordService {
}
