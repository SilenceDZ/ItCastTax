package cn.itcast.nsfw.user.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;


@Controller("userAction")
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	private List<User> userList;
	private User user;
	private String[] selectedRow;
	
	//列表
	public String listUI(){
		userList = userService.findObjects();		
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		return "addUI";
	}
	//保存新增
	public String add(){
		if(user!=null){
			userService.save(user); 
		}
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		if(user!=null &&  StringUtils.isNotBlank(user.getId())){
			user=userService.findObectsById(user.getId());
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		System.out.println("UserAction.edit()");
		if(user!=null ){			
			userService.update(user);
		}
		return "list";
	}
	//根据id删除
	public String delete(){
		if(user!=null &&  StringUtils.isNotBlank(user.getId())){
			userService.delete(user.getId());
		}
		return "list";
	}
	//列批量删除
	public String deleteSelected(){
		if(selectedRow!=null){
			userService.deleteAll(selectedRow);
		}
		return "list";
	}
	//getter setter
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	
	
	
	
}
