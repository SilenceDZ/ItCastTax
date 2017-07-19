package cn.itcasttax.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.itcasttax.service.ITestService;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class TestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ITestService spring;
	
	public String test(){
		spring.say();
		return SUCCESS;
	}
}
