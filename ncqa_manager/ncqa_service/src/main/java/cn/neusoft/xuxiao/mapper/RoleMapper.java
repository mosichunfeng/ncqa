package cn.neusoft.xuxiao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.neusoft.xuxiao.entity.Role;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据adminID删除角色关系
     * @param adminId
     * @return
     */
    boolean delRoleByAdmin(@Param("adminId") Serializable adminId);

    /**
     * 批量插入角色用户关系
     * @param adminId
     * @param roles
     * @return
     */
    boolean bathInsertAdminRole(@Param("adminId") String adminId, @Param("roles") String[] roles);

    /**
     *查询角色已经拥有的所有角色ID
     * @param adminId
     * @return
     */
    List<String> findByAdmin(@Param("adminId") String adminId);

    /**
     * 删除角色权限关系
     * @param roleId
     * @return
     */
    Integer delResByRole(@Param("roleId") String roleId);

    /**
     * 批量添加角色的权限
     * @param roleId
     * @param res
     * @return
     */
    Integer bathInsertRes(@Param("roleId") String roleId, @Param("res") List<String> res);
}