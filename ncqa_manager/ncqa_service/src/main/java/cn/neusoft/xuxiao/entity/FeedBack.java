package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("feedback")
public class FeedBack extends BaseEntity<FeedBack> {
    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private String userId;

    private String title;

    private String content;

    @TableField("phone_type")
    private String phoneType;
}
