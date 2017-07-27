package cn.itcast.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.itcast.nsfw.user.entity.User;

public interface UserService {
	public void save(User entity);
	
	public void update(User entity);
		
	public void delete(Serializable id);
	
	public void deleteAll(Serializable[] ids);
	
	public List<User> findObjects();
		
	public User findObectsById(Serializable id);

	/**
	 *Title:exportExcel
	 *Description:导出用户列表
	 *@param userList
	 *@param outputStream
	 *Throws
	 */
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream);

	public void importExcel(File userExcel);
	
	/**
	 *Title:findUsersByAccountAndId
	 *Description:根据账号或id查询账号
	 *@param account
	 *@return
	 *Throws
	 */
	public List<User> findUsersByAccountAndId(String account,String id);
	

}
