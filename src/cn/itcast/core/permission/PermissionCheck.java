package cn.itcast.core.permission;

import cn.itcast.nsfw.user.entity.User;

public interface PermissionCheck {
	
	/**
	 *Title:isAccessable
	 *Description:校验user中是否包含code对应的权限
	 *@param user 用户
	 *@param code 权限对应的值
	 *@return
	 *Throws
	 */
	public boolean isAccessable(User user,String code);
}
