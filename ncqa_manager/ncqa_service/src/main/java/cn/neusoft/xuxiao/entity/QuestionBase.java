package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("question_base")
public class QuestionBase extends BaseEntity<QuestionBase> {
    private static final long serialVersionUID = 1L;
    /**
     * 题库名
     */
    private String name;

    /**
     * 类别id
     */
    @TableField("kind_id")
    private String kindId;

    private String description;

    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long startTime;

    @TableField(exist = false)
    private String kindName;

    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long endTime;

    private int available;


}
