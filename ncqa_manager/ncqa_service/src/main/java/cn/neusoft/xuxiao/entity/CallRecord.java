package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("call_record")
public class CallRecord extends BaseEntity<CallRecord> {

    @TableField("call_id")
    private String callId;

    @TableField("class_id")
    private String classId;

    @TableField("class_name")
    private String className;
}
