package cn.itcast.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.core.permission.PermissionCheck;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import cn.itcast.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {
	@Resource
	private UserService userService;
	
	@Override
	public boolean isAccessable(User user, String code) {
		//1获取用户对应的所有权限集合
		//权限查询耗时多！！！所以把查询到的权限放在session中,解放数据库 
//		List<UserRole> userRoles=userService.findUserRolesByUserId(user.getId());
		//直接在登录阶段查询权限，并放在session中
		List<UserRole> userRoles=user.getUserRole();
		//应对 为null的情况
		if(userRoles==null){
			userRoles=userService.findUserRolesByUserId(user.getId());
		}
		//2将code与用户权限集合进行比较
		Role role=null;
		for(UserRole ur:userRoles){
			role = ur.getId().getRole();
			for(RolePrivilege rp:role.getRolePrivileges()){
				if(code.equals(rp.getId().getCode())){
					return true;
				}
			}
		}
		return false;
	}

}
