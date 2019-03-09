package cn.neusoft.xuxiao.service.impl;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.magicbeans.base.BaseServiceImp;
import cn.neusoft.xuxiao.entity.Role;
import cn.neusoft.xuxiao.mapper.RoleMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.neusoft.xuxiao.service.IRoleService;

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
@CacheConfig(cacheNames ={ "role"})
public class RoleServiceImpl extends BaseServiceImp<RoleMapper, Role> implements IRoleService {


    @Override
    public List<Role> selectAll() {
        return mapper.selectList(null);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean delRoleByAdmin(String adminId) {
        return mapper.delRoleByAdmin(adminId);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean bathInsert(String adminId, String[] roles) {
        return mapper.bathInsertAdminRole(adminId,roles);
    }

    @Override
    public List<String> findByAdmin(String id) {
        return mapper.findByAdmin(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean delResByRole(String roleId) {
        return SqlHelper.retBool(mapper.delResByRole(roleId));
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean bathInsertRes(String roleId, List<String> res) {
        return SqlHelper.retBool(mapper.bathInsertRes(roleId,res));
    }
}
