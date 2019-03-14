package cn.neusoft.xuxiao.mapper;

import cn.neusoft.xuxiao.entity.Call;
import cn.neusoft.xuxiao.entity.CallRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface CallMapper extends BaseMapper<Call> {
    List<CallRecord> findRecordByCallId(String callId);
}
