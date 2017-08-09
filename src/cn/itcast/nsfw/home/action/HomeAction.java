package cn.itcast.nsfw.home.action;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 调整到纳税服务子系统首页
	 */
	public String frame(){
		return "frame";
	}
	/**
	 * 调整到纳税服务子系统首页-顶部
	 */
	public String top(){
		return "top";
	}
	/**
	 * 调整到纳税服务子系统首页-左边菜单栏
	 */
	public String left(){
		return "left";
	}
}
