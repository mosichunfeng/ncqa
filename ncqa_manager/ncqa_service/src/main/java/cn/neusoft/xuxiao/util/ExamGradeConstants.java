package cn.neusoft.xuxiao.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 答题称号常量
 */
public class ExamGradeConstants {

    public static Map<Integer,String> examDes = new HashMap<>();
    static
    {
        examDes.put(100, "词仙");
        examDes.put(90, "词霸");
        examDes.put(80, "词人");
        examDes.put(70, "词弱");
        examDes.put(60, "词渣");
    }
}
