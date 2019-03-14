package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.Student;
import cn.neusoft.xuxiao.mapper.StudentMapper;
import cn.neusoft.xuxiao.service.IStudentService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl extends BaseServiceImp<StudentMapper,Student> implements IStudentService {
    @Override
    public Student findStudentByUid(String uid) {
        return this.mapper.findStudentByUid(uid);
    }

    @Override
    public Student findStudentByStudentId(String studentId) {
        return mapper.findStudentByStudentId(studentId);
    }

    @Override
    public void insertStudentBatch(List<Student> studentList) {
        mapper.insertStudentBatch(studentList);
    }
}
