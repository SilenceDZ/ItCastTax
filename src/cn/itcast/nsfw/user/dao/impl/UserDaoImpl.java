package cn.itcast.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;

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
		super.getHibernateTemplate().deleteAll(users);
	}
	
}
