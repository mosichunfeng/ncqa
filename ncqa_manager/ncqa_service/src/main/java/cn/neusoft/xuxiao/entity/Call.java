package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@TableName("call_info")
public class Call extends BaseEntity<Call> {
    private static final long serialVersionUID = 1L;

    private int status;

    @TableField(exist = false)
    private List<CallRecord> callRecordList;

    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long startTime;

    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long endTime;
}
