package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.ws.BindingType;

@Data
@TableName("register")
public class Register extends BaseEntity<Register> {

    @TableField("student_id")
    private String studentId;

    @TableField("student_name")
    private String studentName;

    @TableField("student_class_id")
    private String studentClassId;

    @TableField("student_class")
    private String studentClass;

    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long startTime;

    @TableField("image_url")
    private String imageUrl;

    private String address;

    private int status;
}
