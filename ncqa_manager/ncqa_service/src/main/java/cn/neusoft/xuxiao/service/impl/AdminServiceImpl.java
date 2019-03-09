package cn.neusoft.xuxiao.service.impl;

import com.magicbeans.base.BaseServiceImp;
import cn.neusoft.xuxiao.entity.Admin;
import cn.neusoft.xuxiao.mapper.AdminMapper;
import cn.neusoft.xuxiao.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import cn.neusoft.xuxiao.service.IAdminService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
@Service
@Transactional
public class AdminServiceImpl extends BaseServiceImp<AdminMapper, Admin> implements IAdminService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Admin findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public boolean insertOrUpdate(Admin admin, String[] roles) {
        try {
            if(StringUtils.isEmpty(admin.getId())){
                mapper.insert(admin);
            }else{
                mapper.updateById(admin);
            }
            roleMapper.delRoleByAdmin(admin.getId());
            roleMapper.bathInsertAdminRole(admin.getId(),roles);
        } catch (Exception e) {
           logger.error("保存管理员角色失败",e);
            return false;
        }
        return true;
    }

    @Override
    public List<Admin> findAll() {
        return mapper.selectList(null);
    }

}
