package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.github.crab2died.annotation.ExcelField;
import com.magicbeans.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@TableName("exam_history")
public class ExamHistory extends BaseEntity<ExamHistory> {
    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private String userId;

    @TableField(exist = false)
    @ExcelField(title="学号",order = 0)
    private String studentId;

    @ExcelField(title = "姓名" ,order = 1)
    @TableField(exist = false)
    private String studentName;

    @ExcelField(title = "班级" ,order = 2)
    @TableField(exist = false)
    private String studentClass;

    @TableField("question_base_id")
    private String questionBaseId;

    @ExcelField(title = "题库" ,order = 3)
    @TableField(exist = false)
    private String questionBaseName;

    @ExcelField(title = "答题码" ,order = 4)
    @TableField("activity_code")
    private String activityCode;

    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long startTime;

    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long endTime;

    private int status;

    @ExcelField(title = "分数" ,order = 8)
    private int grade;

    @TableField(exist = false)
    @ExcelField(title = "开始时间",order = 5)
    private String start;

    @TableField(exist = false)
    @ExcelField(title = "结束时间",order = 6)
    private String end;

    @TableField(exist = false)
    @ExcelField(title = "状态",order = 7)
    private String finished;


}
