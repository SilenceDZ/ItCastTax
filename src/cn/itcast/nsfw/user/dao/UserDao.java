package cn.itcast.nsfw.user.dao;




import java.io.Serializable;
import java.util.List;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

/**
 * @author leo
 * @ClassName :UserDao
 * @Description:用户dao，因为继承了basedao接口的基本curd方法，暂时不需要添加方法。
 * @date:2017年7月22日上午11:10:53
 */
public interface UserDao extends BaseDao<User> {
	public void deleteAll(Serializable[] ids);
	
	public List<User> findUsersByAccountAndId(String account,String id);

	/**
	 *Title:saveUserRole
	 *Description:保存用户角色
	 *@param userRole
	 *Throws
	 */
	public void saveUserRole(UserRole userRole);

	/**
	 *Title:deleteUserRoleByUserId
	 *Description:根据用户id删除用户角色
	 *@param id
	 *Throws
	 */
	public void deleteUserRoleByUserId(Serializable id);

	/**
	 *Title:findUserRolesByUserId
	 *Description:通过用户id查找用户角色
	 *@param id
	 *@return
	 *Throws
	 */
	public List<UserRole> findUserRolesByUserId(String id);

}
