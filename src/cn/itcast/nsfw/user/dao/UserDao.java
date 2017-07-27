package cn.itcast.nsfw.user.dao;




import java.io.Serializable;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;

/**
 * @author leo
 * @ClassName :UserDao
 * @Description:用户dao，因为继承了basedao接口的基本curd方法，暂时不需要添加方法。
 * @date:2017年7月22日上午11:10:53
 */
public interface UserDao extends BaseDao<User> {
	public void deleteAll(Serializable[] ids);
}
