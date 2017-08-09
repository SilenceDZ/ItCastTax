package cn.itcast.login.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * 条状到登录页面
	 */
	public String toLoginUI(){
		return "loginUI";
	}
	
	public void login(){
	
	}
	
}
