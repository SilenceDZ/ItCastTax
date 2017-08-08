package cn.itcast.nsfw.role.action;

import java.util.HashSet;
import java.util.List;





import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.constant.Constant;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.role.entity.RolePrivilegeId;
import cn.itcast.nsfw.role.service.RoleService;
@Controller
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private RoleService roleService;
	private Role role;
	private List<Role> roleList;
	private String []privilegeIds;
	
	//列表
	public String listUI(){
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVATE_MAP);
		roleList = roleService.findObjects();
		
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		//加载系统权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVATE_MAP);
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(role!=null){
				//处理角色权限
				if(privilegeIds!=null){
					Set<RolePrivilege> set=new HashSet<RolePrivilege>();
					for (String  code : privilegeIds) {
						set.add(new RolePrivilege(new RolePrivilegeId(role, code)));
					}
					role.setRolePrivileges(set);
				}
				roleService.save(role); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVATE_MAP);
		if(role!=null &&  StringUtils.isNotBlank(role.getRoleId())){
			role=roleService.findObectsById(role.getRoleId());
			//处理角色权限的回显
			//懒加载的问题，因为RolePirivileges在action中是懒加载，所以在action中无法获取
			//方式1：lazy=“false”，数据量少时的做法
			//方式2：。。。
			if(role.getRolePrivileges()!=null&&role.getRolePrivileges().size()>0){
				privilegeIds=new String [role.getRolePrivileges().size()];
				int i=0;
				for(RolePrivilege rp: role.getRolePrivileges()){
					privilegeIds[i++]=rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(role!=null){		
				//处理角色权限
				if(privilegeIds!=null){
					Set<RolePrivilege> set=new HashSet<RolePrivilege>();
					for (String  code : privilegeIds) {
						set.add(new RolePrivilege(new RolePrivilegeId(role, code)));
					}
					role.setRolePrivileges(set);
				}
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
	
	/*public List<Role> getUserList() {
		return roleList;
	}
	public void setUserList(List<Role> userList) {
		this.roleList = userList;
	}*/
	//修改了变量名称之后需要把get和set方法也修改了，前端页面是根据这个方法来获取变量的
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}	
	
}
