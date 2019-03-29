package cn.neusoft.xuxiao.service.impl;

import cn.neusoft.xuxiao.entity.ExamDetail;
import cn.neusoft.xuxiao.entity.ExamHistory;
import cn.neusoft.xuxiao.mapper.ExamHistoryMapper;
import cn.neusoft.xuxiao.service.IExamDetailService;
import cn.neusoft.xuxiao.service.IExamHistoryService;
import com.magicbeans.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamHistoryServiceImpl extends BaseServiceImp<ExamHistoryMapper,ExamHistory> implements IExamHistoryService {

}
