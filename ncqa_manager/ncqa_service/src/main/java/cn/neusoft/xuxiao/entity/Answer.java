package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("answer")
public class Answer extends BaseEntity<Answer> {

    private static final long serialVersionUID = 1L;

    @TableField("question_id")
    private String questionId;

    @TableField("answer_index")
    private String answerIndex;

    @TableField("answer_content")
    private String answerContent;

    @TableField("answer_type")
    private int answerType;

    @TableField("is_right")
    private int isRight;
}
