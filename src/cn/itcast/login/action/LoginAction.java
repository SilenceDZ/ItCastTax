package cn.itcast.login.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.itcast.core.constant.Constant;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
@Controller
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private String loginResult;
	
	@Resource
	private UserService userService;
	/**
	 * 条状到登录页面
	 */
	public String toLoginUI(){
		return "loginUI";
	}
	
	/**
	 *Title:login
	 *Description:用户内容
	 *Throws
	 * @return 
	 */
	public String login(){
		//1.1、获取帐号、密码
		if(user!=null){
			if(StringUtils.isNotBlank(user.getAccount())&&
					StringUtils.isNotBlank(user.getPassword())){
				//1.2、根据帐号、密码查询用户记录
				List<User> list=userService.findUsersByAccountAndPass(user.getAccount(), user.getPassword());
				if(list!=null &&list.size()>0){
					//1.3、登录成功（有用户记录）
					//1.3.1、获取用户信息
					user=list.get(0);
					//1.3.2、根据用户id将用户的所有角色设置到用户中
					user.setUserRole(userService.findUserRolesByUserId(user.getId()));
					//1.3.3、将用户信息存入session
					HttpSession session = ServletActionContext.getRequest().getSession();
					//这里的标识符，其他开发人员也需要使用，固定写一个字符其他人也需要使用这个字符，可以把这个当成一个
					//字符常量
					session.setAttribute(Constant.USER, user);
					//1.3.4、将用户登录信息记录到日志文件
					Log log = LogFactory.getLog(getClass());
					log.info("用户名称为："+user.getName()+"的用户登录系统个。");
					//1.3.5、跳转到系统首页（重定向）
					return "home";
				}else {
					//1.4、登录失败（没有查询到用户记录）；跳转到登录页面并提示用户登录失败
					loginResult="登录失败！账号或密码错误！";					
				}				
			}else {
				loginResult = "帐号或密码不能为空！";
			}
		}else{
			loginResult="账号密码不能为空";
		}		
		return toLoginUI();
	}
	/**
	 *Title:logout
	 *Description:注销登录
	 *Throws
	 * @return 
	 */
	public String logout(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute(Constant.USER);
		return toLoginUI(); 
	}
	/**
	 *Title:toNoPermissionUI
	 *Description:跳转到没有权限提示页面
	 *@return
	 *Throws
	 */
	public String toNoPermissionUI(){
		return "noPermissionUI";
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
}
