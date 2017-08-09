<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="${ctx}/css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript">

function logins(){
	document.forms[0].submit();
}

function setClean(){
	document.getElementById("account").value = "";
	document.getElementById("password").value = "";
}

//处理登录页面嵌套的问题
if(window != window.parent){
	window.parent.location.reload(true);
}

</script>
<style type="text/css">
html { overflow-y: hidden;  }

.password{
      background-color:#FFEC8B;
	  border:1px solid #f1f3f6;
	  font-color:#ccc;
	  height: 35px;
}

#Layer1 {
	position:absolute;
	left:224px;
	top:479px;
	padding-top:5px;
	width:99px;
	height:21px;
	background-color:#fff;
	z-index:1;
}
.password1 {      
	 background-color:#f1f3f6;
	  border:1px solid #f1f3f6;
	  font-color:#ccc;
}

.youbian input{ border:0px none; background-color:transparent; color:#555;padding-left:10px;font-size:16px;width:100%;overflow: hidden;}
</style>
    <!--[if IE 6]>
    <script type="text/javascript" src="${ctx}/ehome/js/DD_belatedPNG.js" ></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('b, s, img, span, .prev, .next, a, input, .youbian, td');
    </script>
    <![endif]-->
</head>
<body scroll="no">

	<div id="lo_tf">
	<div class="outside">
		<!-- 头部 -->
	    <div class="head">
	      <div>
			<div style="width:840; padding-left: 200px;" align="left">
			<img src="${ctx}/images/login/form_03.png"   width="332" height="47"/>
			</div>	      
	      </div>
	    </div>
    	<!-- 中间 -->
	    <div class="main2">
		   <div class="content">  
		   <div class="youbian">
		   	<div style="margin-bottom: 100px;width: 251px;padding-top: 100px">
		   	<!--debug日志： 使用了简单的主题后s：textfield标签就不会独占一行 -->
		   	<s:form theme="simple" name="form-login" namespace="/sys" action="login_login">
		   		<table >
		   			<tr style="height: 50px">
		   				<td>
		   					<h3>账号</h3>
		   				</td>
		   				<td >
			   				<s:textfield id="account" name="user.account"  cssClass="password"
			   				 cssStyle="color: #767676;background-color:#FFEC8B;" size="15"/>
			   			</td><!-- <input  style="height: 30px;background-color:#FFEC8B " type="text"/> -->
		   			</tr>
		   			<tr style="height: 50px">
		   				<td><h3>密码</h3></td>
		   				<td>
		   					<s:password id="password" name="user.password" cssClass="password"  
		   					cssStyle="color: #767676;background-color:#FFEC8B;" size="15"/>
		   				</td>
		   				<!-- <input style="height: 30px;background-color:#FFEC8B " type="password"/> -->
		   			</tr>
		   			<tr style="height: 50px">
		   				<td  align="left">
		   				<a href="#" onclick="javascript:logins();">
		   					<img src="${ctx}/images/login/form_15.png" width="85" height="37"/>
		   				</a>
		   				<!-- <button type="submit" ><h3>登录</h3></button> --></td>
		   				<td align="right">
		   					<img src="${ctx}/images/login/form_17.png" width="85" height="37" onclick="setClean()"/>
		   				</td>
		   				<!-- <td width="50px" align="center"><button type="reset"><h3>清除</h3></button></td> -->
		   			</tr>
		   		</table>
		   	</s:form></div>
		  </div>   
	       </div>
	   </div><!-- end main2 -->
	
	
	<div class="foot">版权所有&nbsp;|&nbsp;国税局&nbsp;&nbsp;2014年</div>
	</div><!-- end outside -->

	</div><!--end lo_tf -->

</body>
</html>