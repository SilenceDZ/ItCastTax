package cn.itcast.nsfw.role.dao;

import java.io.Serializable;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	public void deleteAll(Serializable[] ids);

	//根据角色id删除角色下的所有权限
	public void deleteRolePrivilegesByRoleId(String roleId);
	
}
