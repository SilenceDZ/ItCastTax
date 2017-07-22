package cn.itcast.nsfw.user.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public void save(User entity) {
		userDao.save(entity);

	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);;
	}
	
	@Override
	public void deleteAll(Serializable[] ids) {
		userDao.deleteAll(ids);
	}
	
	@Override
	public List<User> findObjects() {
		return userDao.findObjects();
	}

	@Override
	public User findObectsById(Serializable id) {
		return userDao.findObectsById(id);
	}

	

}
