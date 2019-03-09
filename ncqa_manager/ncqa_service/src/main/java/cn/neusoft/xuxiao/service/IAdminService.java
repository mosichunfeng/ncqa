package cn.neusoft.xuxiao.service;

import com.magicbeans.base.BaseService;
import cn.neusoft.xuxiao.entity.Admin;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
public interface IAdminService extends BaseService<Admin> {

    /**
     * 根据名称查询
     * @param username
     * @return
     */
    Admin findByUsername(String username);

    /**
     * 保存管理员和角色信息
     * @param admin
     * @param roles
     * @return
     */
    boolean insertOrUpdate(Admin admin, String[] roles);

    List<Admin> findAll();
}
