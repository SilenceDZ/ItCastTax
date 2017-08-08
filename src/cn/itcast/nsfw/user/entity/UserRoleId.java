package cn.itcast.nsfw.user.entity;

import java.io.Serializable;

import cn.itcast.nsfw.role.entity.Role;

/**
 * @author leo
 * @ClassName :UserRoleID
 * @Description:用户角色表的联合主键类型
 * @date:2017年8月8日下午8:59:50
 */
public class UserRoleId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Role role;
	
	/**
	 * @Fields userId:不会调用对象来完成其他的操作或者业务，可以使用id。
	 * 不是不写User，而是不需要User对象！！！
	 */
	private String userId;
	
	public UserRoleId() {
		super();
	}

	public UserRoleId(Role role, String userId) {
		super();
		this.role = role;
		this.userId = userId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
