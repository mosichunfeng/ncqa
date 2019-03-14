package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("right_answer")
public class RightAnswer extends BaseEntity<RightAnswer> {
    private static final long serialVersionUID = 1L;

    @TableField("question_id")
    private String questionId;

    @TableField("right_answer_index")
    private String rightAnswerIndex;

    @TableField("right_answer_content")
    private String rightAnswerContent;

    @TableField("answer_type")
    private int answerType;
}
