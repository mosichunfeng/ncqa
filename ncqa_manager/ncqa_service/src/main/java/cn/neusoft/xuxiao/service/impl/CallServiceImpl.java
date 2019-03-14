package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Call;
import cn.neusoft.xuxiao.entity.CallRecord;
import cn.neusoft.xuxiao.entity.ClassInfo;
import cn.neusoft.xuxiao.mapper.CallMapper;
import cn.neusoft.xuxiao.service.ICallRecordService;
import cn.neusoft.xuxiao.service.ICallService;
import cn.neusoft.xuxiao.service.IClassInfoService;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CallServiceImpl extends BaseServiceImp<CallMapper, Call> implements ICallService {
    @Autowired
    private IClassInfoService classInfoService;

    @Autowired
    private ICallRecordService callRecordService;

    @Override
    public List<CallRecord> findRecordByCallId(String callId) {
        return mapper.findRecordByCallId(callId);
    }

    @Override
    public void saveCallAndRecord(Call call, String classes) {
        save(call);
        if(!classes.contains(",")){
            addRecord(call, classes);
        }else{
            String[] c = classes.split(",");
            for (String s : c) {
                addRecord(call, s);
            }
        }
    }

    private void addRecord(Call call, String s) {
        CallRecord record = new CallRecord();
        record.setCallId(call.getId());
        ClassInfo classInfo = classInfoService.find(s);
        if (classInfo != null) {
            record.setClassId(classInfo.getId() == null ? "" : classInfo.getId());
            record.setClassName(classInfo.getName() == null ? "" : classInfo.getName());
        }
        callRecordService.save(record);
    }
}
