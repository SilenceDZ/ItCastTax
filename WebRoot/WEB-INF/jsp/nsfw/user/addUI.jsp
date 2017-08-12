<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var res=false;
		function verifyAccount(asyncFlag){
		//undefine与false不等，所以不传值时是异步请求
			if(asyncFlag!=false)asyncFlag=true;
			//1.获取账号
			var $account=$("#account");
			if($account.val()!=""){
				//2.异步查询
				$.ajax({
					url:"${basePath}nsfw/user_verifyAccount.action",
					type:"get",
					async:asyncFlag,
					data:{"user.account":$account.val()},
					success:function(msg){
						//成功的回调方法
						if("true"!=msg){
							alert("该账号已经被使用：请输入其他账号！");
							$account.focus();
							res=false;
						}else{
							res=true;;
						}
					}
				})	
			}
		}
		function checkForm(){
			var $name=$("#name");
			if($name.val()==""){
				alert("用户名不能为空");
				$name.focus();
				return false;
			}
			var $password=$("#password");
			if($password.val()==""){
				alert("密码不能为空");
				$name.focus();
				return false;
			}
			verifyAccount(false);
			/* if(res){
				document.forms[0].submit();
			} */
			return res;
		}
	</script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }nsfw/user_add.action" method="post" onsubmit="return checkForm()" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo"  width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B' }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield id="name" name="user.name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield id="account" name="user.account" onchange="verifyAccount()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield id="password" name="user.password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
            	<s:checkboxlist list="#roleList" listKey="roleId" listValue="name" name="roleIds"></s:checkboxlist>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td><s:textfield id="birthday" name="user.birthday" readonly="true" 
            onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"/></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <div class="tc mt20">
    	<!-- onsubmit属性是写在form表单中的 -->
        <input type="submit" class="btnB2" value="保存"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>