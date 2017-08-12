package cn.itcast.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.core.constant.Constant;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse respongse=(HttpServletResponse) resp;
		//判断是否是登录请求
		//如果地址是登录界面就放行
		String uri = request.getRequestURI();
		if(!uri.contains("sys/login_")){//非登录请求
			//判断session中是否有用户信息
			if(request.getSession().getAttribute(Constant.USER)!=null){//用户已经登录
				chain.doFilter(request, respongse);
			}else{//没有登录，跳转到登录界面
				respongse.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action"); 
			}
		}else{//登录请求
			chain.doFilter(request, respongse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
