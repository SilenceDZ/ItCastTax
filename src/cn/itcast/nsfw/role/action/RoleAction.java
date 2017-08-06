package cn.itcast.nsfw.role.action;

import java.util.List;



import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import cn.itcast.core.action.BaseAction;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.service.RoleService;
@Controller
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private RoleService roleService;
	private List<Role> userList;
	private Role role;
	
	
	//列表
	public String listUI(){
		userList = roleService.findObjects();
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(role!=null){				
				roleService.save(role); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		if(role!=null &&  StringUtils.isNotBlank(role.getRoleId())){
			role=roleService.findObectsById(role.getRoleId());
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(role!=null){				
				roleService.update(role);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//根据id删除
	public String delete(){
		if(role!=null &&  StringUtils.isNotBlank(role.getRoleId())){
			roleService.delete(role.getRoleId());
		}
		return "list";
	}
	//列批量删除
	public String deleteSelected(){
		if(selectedRow!=null){
			roleService.deleteAll(selectedRow);
		}
		return "list";
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public List<Role> getUserList() {
		return userList;
	}
	public void setUserList(List<Role> userList) {
		this.userList = userList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}	
}
