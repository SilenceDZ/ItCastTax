package cn.itcast.nsfw.role.entity;

import java.io.Serializable;

/**
 * @author leo
 * @ClassName :RolePrivilegeId
 * @Description:表示权限的联合主键实体类
 * @date:2017年8月6日下午8:41:15
 */
public class RolePrivilegeId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//角色id 权限id
	private Role role;
	private String code;
	
	public RolePrivilegeId() {
		super();
	}
	public RolePrivilegeId(Role role, String code) {
		super();
		this.role = role;
		this.code = code;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * hibernate中的联合主键必须实现这两个方法，用以实现主键唯一性
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePrivilegeId other = (RolePrivilegeId) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
	
}
