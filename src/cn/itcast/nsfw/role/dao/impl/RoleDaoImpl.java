package cn.itcast.nsfw.role.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;





import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;

@Component("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deleteAll(Serializable[] ids) {
		List<Role> roles = new ArrayList<Role>();
		for (Serializable id : ids) {
			Role Role = findObectsById(id);
			roles.add(Role);
		}
		// super.getHibernateTemplate().deleteAll(users);
		// HibernateTemplate()只在basedao中使用方便日后的修改
		super.deleteAll(roles);
	}

	@Override
	public void deleteRolePrivilegesByRoleId(String roleId) {
		Query query = getCurrentSession().createQuery("delete from RolePrivilege where id.role.roleId=?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

	

}
