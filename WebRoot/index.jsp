<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
/* <!-- 因为默认页面是这个，但是登录界面在Web-INF文件夹下，不允许用户直接访问，所以在这里跳转 --> */
response.sendRedirect(basePath+"sys/login_toLoginUI.action");
%>
