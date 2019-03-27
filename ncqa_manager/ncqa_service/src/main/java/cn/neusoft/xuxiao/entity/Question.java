package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.github.crab2died.annotation.ExcelField;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

import java.util.List;

@TableName("question")
@Data
public class Question extends BaseEntity<Question> {
    private static final long serialVersionUID = 1L;

    @TableField("question_base_id")
    private String questionBaseId;

    @TableField("select_type")
    private int selectType;

    private String content;

    @TableField("question_type")
    private int questionType;

    private int grade;

    @TableField(exist = false)
    private List<Answer> answerList;

    @TableField(exist = false)
    private String questionBaseName;

    @TableField(exist = false)
    private RightAnswer rightAnswer;
}
