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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.core.constant.Constant;
import cn.itcast.core.permission.PermissionCheck;
import cn.itcast.nsfw.user.entity.User;

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
				//判断是否访问纳税服务系统
				if(uri.contains("/nsfw/")){
					User user = (User) request.getSession().getAttribute(Constant.USER);
					//访问纳税服务子系统，需要进行权限判断
					//如何实例化这个对象？
					//注入？LoginFilter不是Action，无法写在bean中
					//再new一个ApplicationContext?,重复加载统一的bena，不合适
					//WebApplicationContextUtils获取的是随着应用服务器启动实实例化的IOD容器
					WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
					PermissionCheck pc=(PermissionCheck) context.getBean("permissionCheck");
					if(pc.isAccessable(user, "nsfw")){//有权限
						chain.doFilter(request, respongse);
					}else{//没有权限，跳转到没有权限提示页面
						respongse.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
					}
				}else{
					//非纳税服务子系统
					chain.doFilter(request, respongse);
				}
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
