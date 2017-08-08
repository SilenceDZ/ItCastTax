package cn.itcast.nsfw.role.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String name;
	private String state;
	
	/**
	 * @Fields rolePrivileges:用户权限，
	 * ！！！如果修改了这里的变量名，对应的get ，set方法和hibernate中的配置文件都应该要修改
	 */
	private Set<RolePrivilege> rolePrivileges=new HashSet<RolePrivilege>();
	//用户状态
	public static String ROLE_STATE_VALID="1";//有效
	public static String ROLE_STATE_INVALID="0";
	public Role() {
		super();
	}
		
	public Role(String roleId, String name, String state,
			Set<RolePrivilege> rolePirivileges) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.state = state;
		this.rolePrivileges = rolePirivileges;
	}
	
	public Role(String roleId) {
		this.roleId=roleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((rolePrivileges == null) ? 0 : rolePrivileges.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (rolePrivileges == null) {
			if (other.rolePrivileges != null)
				return false;
		} else if (!rolePrivileges.equals(other.rolePrivileges))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
}
