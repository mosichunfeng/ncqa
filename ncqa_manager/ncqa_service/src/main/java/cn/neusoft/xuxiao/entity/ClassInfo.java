package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("class")
public class ClassInfo extends BaseEntity<ClassInfo> {
    private static final long serialVersionUID = 1L;

    private String name;

    @TableField("work_detail")
    private int workDetail;

    @TableField(exist = false)
    private int count;
}
