package cn.itcast.nsfw.user.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;


@Controller("userAction")
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	private List<User> userList;
	private User user;
	private String[] selectedRow;
	
	//头像上传需要的属性
	private File headImg;
	private String headImgFileName;
	private String headImgContentType;
	
	//列表
	public String listUI(){
		userList = userService.findObjects();		
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
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
				userService.save(user); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
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
				userService.update(user);  
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
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
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
	
	
	
	
}
