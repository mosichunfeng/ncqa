package cn.neusoft.xuxiao.mapper;

import cn.neusoft.xuxiao.entity.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
    Student findStudentByUid(String uid);

    Student findStudentByStudentId(String studentId);

    void insertStudentBatch(@Param("studentList") List<Student> studentList);
}
