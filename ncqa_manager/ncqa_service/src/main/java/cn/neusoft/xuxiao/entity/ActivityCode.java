package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@TableName("activity_code")
@Data
public class ActivityCode extends BaseEntity<ActivityCode> {
    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private String userId;

    private String code;

    @TableField("question_base_id")
    private String questionBaseId;

}
