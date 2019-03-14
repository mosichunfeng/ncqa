package cn.neusoft.xuxiao.VO;

import com.github.crab2died.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;

@Data
public class FillQuestionDO implements Serializable {

    private String id;

    private String questionBaseId;

    @ExcelField(title = "题目",order = 1)
    private String content;

    @ExcelField(title = "正确答案",order = 2)
    private String rightAnswer;

    @ExcelField(title = "分值",order = 3)
    private int grade;
}
