package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("activity_code")
public class Join extends BaseEntity<Join> {
    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private String userId;

    private String code;

    @TableField("question_base_id")
    private String questionBaseId;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private String studentClass;

    @TableField(exist = false)
    private int finished;

    @TableField(exist = false)
    private String questionBaseName;
}
