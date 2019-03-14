package cn.neusoft.xuxiao.service;

import cn.neusoft.xuxiao.entity.Student;
import com.magicbeans.base.BaseService;

import java.util.List;

public interface IStudentService extends BaseService<Student> {

    Student findStudentByUid(String uid);

    Student findStudentByStudentId(String studentId);

    void insertStudentBatch(List<Student> studentList);
}
