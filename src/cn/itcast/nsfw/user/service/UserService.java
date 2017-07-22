package cn.itcast.nsfw.user.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.nsfw.user.entity.User;

public interface UserService {
	public void save(User entity);
	
	public void update(User entity);
		
	public void delete(Serializable id);
	
	public void deleteAll(Serializable[] ids);
	
	public List<User> findObjects();
		
	public User findObectsById(Serializable id);
	

}
