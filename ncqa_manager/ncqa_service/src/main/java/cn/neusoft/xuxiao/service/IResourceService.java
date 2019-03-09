package cn.neusoft.xuxiao.service;

import cn.neusoft.xuxiao.Tree;
import cn.neusoft.xuxiao.entity.Resource;
import com.magicbeans.base.BaseService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
public interface IResourceService extends BaseService<Resource> {


    /**
     * 根据管理员ID查询资源Code
     * @param id
     * @return
     */
    Set<String> findMenuCodeByUserId(String id);

    /**
     * 根据角色查询资源信息
     * @param roleId
     * @return
     */
    List<Resource> selectRoleResource(String roleId);

    /**
     * 查询所有
     * @return
     */
    List<Resource> selectAll();


    /**
     * 根据用户查询用户的菜单信息
     * @param adminId
     * @return
     */
    List<Tree> selectAdminMenu(String adminId);


}
