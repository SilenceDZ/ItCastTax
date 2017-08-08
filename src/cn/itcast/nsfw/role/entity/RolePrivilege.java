package cn.itcast.nsfw.role.entity;

/**
 * @author leo
 * @ClassName :RolePrivilege
 * @Description:用户权限实体
 * @date:2017年8月6日下午8:40:10
 */
public class RolePrivilege {
	private RolePrivilegeId id;
	
	public RolePrivilege() {
		super();
	}

	public RolePrivilege(RolePrivilegeId id) {
		super();
		this.id = id;
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RolePrivilege [id=" + id + "]";
	}
	
	
}
