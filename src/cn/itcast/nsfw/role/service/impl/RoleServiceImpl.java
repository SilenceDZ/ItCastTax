package cn.itcast.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		//1.将该角色对应的所有权限删除
		roleDao.deleteRolePrivilegesByRoleId(role.getRoleId());
		//2.更新
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id); 
	}

	@Override
	public void deleteAll(Serializable[] ids) {
		roleDao.deleteAll(ids);
	}

	@Override
	public List<Role> findObjects() {
		return roleDao.findObjects();
	}

	@Override
	public Role findObectsById(Serializable id) {
		return roleDao.findObectsById(id);
	}

}
