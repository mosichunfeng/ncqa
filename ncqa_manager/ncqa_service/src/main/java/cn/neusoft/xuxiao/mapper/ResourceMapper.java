package cn.neusoft.xuxiao.mapper;

import cn.neusoft.xuxiao.entity.Resource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
@CacheNamespaceRef(ResourceMapper.class)
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据adminID查询
     * @param adminId
     * @return
     */
    Set<String> findMenuCodeByUserId(@Param("adminId") String adminId);


    /**
     * 根据角色查询资源信息
     * @param roleId
     * @return
     */
    List<Resource> selectRoleResource(@Param("roleId") String roleId);


    /**
     * 根据权限删除和角色的关系
     * @param resId
     * @return
     */
    Integer delRoleResource(@Param("resId") Serializable resId);


    /**
     * 根据用户查询用户的菜单信息
     * @param adminId
     * @return
     */
    List<Resource> selectAdminMenu(@Param("adminId") String adminId);


}