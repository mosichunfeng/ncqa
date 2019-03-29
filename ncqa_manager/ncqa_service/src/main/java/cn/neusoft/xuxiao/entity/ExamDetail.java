package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("exam_detail")
public class ExamDetail extends BaseEntity<ExamDetail> {
    private static final long serialVersionUID = 1L;

    /**
     * 答题码
     */
    private String code;

    /**
     * 答题历史id
     */
    @TableField("exam_history_id")
    private String examHistoryId;

    @TableField("questionId")
    private String questionId;

    @TableField("answer_content")
    private String answerContent;

    @TableField("question_type")
    private String questionType;

    private String grade;
}
