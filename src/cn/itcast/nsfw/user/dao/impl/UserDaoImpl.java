package cn.itcast.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

/**
 * @author leo
 * @ClassName :UserDaoImpl
 * @Description:UserDao实现类。基本的crud已经在BaseDaoImpl中实现，此处不需要在实现
 * @date:2017年7月22日上午11:15:05
 */
@Component("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@Override
	public void deleteAll(Serializable[] ids) {
		List<User> users=new ArrayList<User>();
		for (Serializable id : ids) {
			User user=findObectsById(id);
			users.add(user);
		}
//		super.getHibernateTemplate().deleteAll(users);
		//HibernateTemplate()只在basedao中使用方便日后的修改
		super.deleteAll(users);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByAccountAndId(String account,String id) {
		//hql中的表区分大小写！！！
		String sSQL=" from User where account = ?";
		if(StringUtils.isNotBlank(id)){
			sSQL+=" and id !=?";			
		}
		System.out.println(account);
		System.out.println(id);
		Query query = getCurrentSession().createQuery(sSQL);
 		query.setParameter(0, account);
 		if(StringUtils.isNotBlank(id)){
 			query.setParameter(1, id);
 		}
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}
	/**
	 * 删除用户的角色
	 */
	@Override
	public void deleteUserRoleByUserId(Serializable id) {
 		Query query = getCurrentSession().createQuery("delete from UserRole where id.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findUserRolesByUserId(String id) {
		Query query = getCurrentSession().createQuery(" from UserRole where id.userId=?");
		query.setParameter(0, id);
		return query.list();
	}
	
}
