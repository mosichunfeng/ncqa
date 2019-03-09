package cn.neusoft.xuxiao.service;

import com.magicbeans.base.BaseService;
import cn.neusoft.xuxiao.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
public interface IRoleService extends BaseService<Role> {

    /**
     * 查询所有的角色
     * @return
     */
    List<Role> selectAll();

    /**
     * 根据admin删除角色关系
     * @param id
     * @return
     */
    boolean delRoleByAdmin(String id);

    /**
     * 批量插入角色用户关系
     * @param id
     * @param roles
     * @return
     */
    boolean bathInsert(String id, String[] roles);

    /**
     * 查询角色已经拥有的所有角色ID
     * @param id
     * @return
     */
    List<String> findByAdmin(String id);

    /**
     * 删除角色权限关系
     * @param roleId
     * @return
     */
    boolean delResByRole(String roleId);

    /**
     * 批量添加角色的权限
     * @param roleId
     * @param res
     * @return
     */
    boolean bathInsertRes(String roleId, List<String> res);

}
