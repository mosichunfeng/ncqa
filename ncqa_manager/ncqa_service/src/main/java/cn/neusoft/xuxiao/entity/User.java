package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("wx_user")
public class User extends BaseEntity<User> {
    private static final long serialVersionUID = 1L;

    @TableField("open_id")
    private String openId;

    @TableField("student_id")
    private String studentId;
}
