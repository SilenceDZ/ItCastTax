package cn.itcast.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.exception.ActionException;
import cn.itcast.core.exception.ServiceException;
import cn.itcast.nsfw.role.service.RoleService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;



@Controller("userAction")
public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	@Resource
	private RoleService roleService;
	private List<User> userList;
	private User user;
	
	
	//头像上传需要的属性
	private File headImg;
	private String headImgFileName;
	private String headImgContentType;
	//导入用户列表需要的属性，实际上可以和前面3个属性公用
	private File userExcel;
	private String userExcelFileName;
	private String userExcelContentType;
	
	//接收角色id
	private String []roleIds;
	
	//列表
	public String listUI()throws ActionException{
		try {
			userList = userService.findObjects();
		} catch (ServiceException e) {
			throw new ActionException("请求操作失败!"+e.getErrorMsg());
		}		
//		return "error";
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		//加载角色列表
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(user!=null){
				//处理头像
				if(headImg!=null){//1.获取头像文件
					//2保存头像文件
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user"); 
					//名字要唯一！！
					//生成的UUID中间有4个“-”	
					String fileName=UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					System.out.println(filePath);
					System.out.println(fileName);
					FileUtils.copyFile(headImg, new File(filePath,fileName));
					//3设置用户头像路径
					//要设置相对路径
					user.setHeadImg("user/"+fileName);
				}
				userService.saveUserAndRole(user,roleIds); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		//加载角色列表
				ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		if(user!=null &&  StringUtils.isNotBlank(user.getId())){
			user=userService.findObectsById(user.getId());
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(user!=null){
				//处理头像
				if(headImg!=null){//1.获取头像文件
					//2保存头像文件
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user"); 
					//名字要唯一！！
					String fileName=UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(filePath,fileName));
					//3设置用户头像路径
					//要设置相对路径
					user.setHeadImg("user/"+fileName);
				}
				userService.updateUserAndRole(user,roleIds);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//根据id删除
	public String delete(){
		if(user!=null &&  StringUtils.isNotBlank(user.getId())){
			userService.delete(user.getId());
		}
		return "list";
	}
	//列批量删除
	public String deleteSelected(){
		if(selectedRow!=null){
			userService.deleteAll(selectedRow);
		}
		return "list";
	}
	
	/**
	 *Title:exportExcel
	 *Description:导出用户列表
	 *Throws
	 */
	public void exportExcel(){		
		try {
			//1.获取用户列表
			userList=userService.findObjects();
			//2.输出excel
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(userList,outputStream);
			if( outputStream!=null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//导入用户列表
	public String importExcel(){
		System.out.println("UserAction.importExcel()");
		if(userExcel!=null){
			//判断是否是excel文件
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				userService.importExcel(userExcel);
			}
		}
		return "list";
	}
	//检验账号唯一性
	public void verifyAccount(){
		try {
			//1.获取账号id
			if(user!=null && StringUtils.isNotBlank(user.getAccount())){
				String res="true";
				//2.根据账号、id查询记录
				List<User> userList=userService.findUsersByAccountAndId(user.getAccount(),user.getId());
				if(userList!=null &&userList.size()>0){
					res="false";
				}
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(res.getBytes());
				outputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	//getter setter
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
		
}
