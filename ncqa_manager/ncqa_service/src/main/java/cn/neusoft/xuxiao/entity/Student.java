package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.github.crab2died.annotation.ExcelField;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("student")
public class Student extends BaseEntity<Student> {
    private static final long serialVersionUID = 1L;

    @ExcelField(title = "学号" ,order = 1)
    @TableField("student_id")
    private String studentId;

    @ExcelField(title = "姓名" ,order = 2)
    @TableField("student_name")
    private String studentName;

    @ExcelField(title = "专业" ,order = 3)
    @TableField("student_class")
    private String studentClass;

    @TableField("student_class_id")
    private String studentClassId;

    @ExcelField(title = "性别" ,order = 4)
    @TableField("student_gender")
    private String studentGender;

    @ExcelField(title = "手机号" ,order = 5)
    @TableField("student_tel")
    private String studentTel;

    @TableField("work_detail")
    private int workDetail;

}
