package cn.itcast.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.result.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * @author leo
 * @ClassName :SysActionResult
 * @Description:做复杂的异常处理
 * @date:2017年7月27日下午5:43:22
 */
public class SysActionResult extends StrutsResultSupport {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doExecute(String arg0, ActionInvocation arg1)
			throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction) arg1.getAction();
		
		System.out.println("SysActionResult.doExecute()");
	}

}
