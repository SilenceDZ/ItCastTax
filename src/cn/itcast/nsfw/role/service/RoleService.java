package cn.itcast.nsfw.role.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.nsfw.role.entity.Role;

public interface RoleService {
	public void save(Role entity);
	
	public void update(Role entity);
		
	public void delete(Serializable id);
	
	public void deleteAll(Serializable[] ids);
	
	public List<Role> findObjects();
	
	public Role findObectsById(Serializable id);
}
