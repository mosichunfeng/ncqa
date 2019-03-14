package cn.neusoft.xuxiao.VO;


import cn.neusoft.xuxiao.entity.Answer;
import com.github.crab2died.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SelectQuestionDO implements Serializable {
    private String id;

    @ExcelField(title = "选项类型",order = 2)
    private int selectType;

    @ExcelField(title = "问题", order = 1)
    private String content;

    private int questionType;

    @ExcelField(title = "选项A",order = 3)
    private String answerA;

    @ExcelField(title = "选项B",order = 4)
    private String answerB;

    @ExcelField(title = "选项C",order = 5)
    private String answerC;

    @ExcelField(title = "选项D",order = 6)
    private String answerD;

    @ExcelField(title = "正确答案")
    private String rightAnswer;

    @ExcelField(title = "分值",order = 7)
    private int grade;

    @ExcelField(title = "问题编号",order = 0)
    private int index;

    private String questionBaseId;

}
