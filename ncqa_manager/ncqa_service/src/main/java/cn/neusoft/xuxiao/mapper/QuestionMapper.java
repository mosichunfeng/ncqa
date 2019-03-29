package cn.neusoft.xuxiao.mapper;

import cn.neusoft.xuxiao.entity.Question;
import cn.neusoft.xuxiao.entity.RightAnswer;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionMapper extends BaseMapper<Question> {
    Map<String,String> findFillAnswerRefByBaseIdAndType(@Param("base_id") String questionBaseId);

    Map<String,String> findSelectAnswerRefByBaseIdAndType(@Param("base_id") String questionBaseId);
}
