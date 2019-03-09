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
public class Admin extends BaseEntity<Admin> {

    private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private String salt;

	private Boolean enable = false;

	@TableField(exist=false)
	private String[] roles;

	@TableField(value = "is_system")
	private Boolean isSystem = Boolean.TRUE ;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public Boolean getSystem() {
		return isSystem;
	}

	public void setSystem(Boolean system) {
		isSystem = system;
	}

	@Override
	public String toString() {
		return "Admin{" +
			"id=" + id +
			", username=" + username +
			", password=" + password +
			", isSystem=" + isSystem +
			"}";
	}


}
