package cn.itcasttax.action.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.itcasttax.service.impl.SpringIocTest;

import com.opensymphony.xwork2.ActionSupport;
@Controller
public class StrutsTestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private SpringIocTest spring;
	public String test(){
		spring.say();
		return SUCCESS;
	}
}
