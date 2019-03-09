package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.magicbeans.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
public class Role extends BaseEntity<Role> {

    private static final long serialVersionUID = 1L;

	@TableField("role_name")
	private String roleName;

	@TableField("role_desc")
	private String roleDesc;

	@TableField("is_system")
	private Boolean isSystem = Boolean.TRUE;

	private Integer enable;



	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Boolean getSystem() {
		return isSystem;
	}

	public void setSystem(Boolean system) {
		isSystem = system;
	}

	@Override
	public String toString() {
		return "Role{" +
			"id=" + id +
			", roleName=" + roleName +
			", roleDesc=" + roleDesc +
			", enable=" + enable +
			", createdAt=" + createTime +
			", updatedAt=" + updateTime +
			"}";
	}
}
