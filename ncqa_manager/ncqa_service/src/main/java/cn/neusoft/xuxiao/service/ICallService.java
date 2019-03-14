package cn.neusoft.xuxiao.service;

import cn.neusoft.xuxiao.entity.Call;
import cn.neusoft.xuxiao.entity.CallRecord;
import com.magicbeans.base.BaseService;

import java.util.List;

public interface ICallService extends BaseService<Call> {
    List<CallRecord> findRecordByCallId(String callId);

    void saveCallAndRecord(Call call, String classes);
}
