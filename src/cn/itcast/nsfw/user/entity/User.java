package cn.itcast.nsfw.user.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String dept;
	private String name;
	private String account;
	private String password;
	/**
	 * @Fields headImg:用户图像
	 */
	private String headImg;
	private boolean gender;
	/**
	 * @Fields role:用户和角色，多对多关系。
	 * 		一般不适用Hibernate的多对多做
	 * 1.使得user的关系太多，查询过多的无用信息
	 * 2.用户模块不是由自己开发，不能随意更改用户的类
	 */
//	private Set<> role;
	private String state;
	private String mobile;
	private String email;
	private Date birthday;
	private String memo;
	
	public static String USER_STATE_VALID="1";//用户状态有效
	public static String USER_STATE_INVALID="0";//用户状态无效
	
	public User() {
		super();
	}
	public User(String id, String dept, String name, String account,
			String password, String headImg, boolean gender, String state,
			String mobile, String email, Date birthday, String memo) {
		super();
		this.id = id;
		this.dept = dept;
		this.name = name;
		this.account = account;
		this.password = password;
		this.headImg = headImg;
		this.gender = gender;
		this.state = state;
		this.mobile = mobile;
		this.email = email;
		this.birthday = birthday;
		this.memo = memo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", dept=" + dept + ", name=" + name
				+ ", account=" + account + ", password=" + password
				+ ", headImg=" + headImg + ", gender=" + gender + ", state="
				+ state + ", mobile=" + mobile + ", email=" + email
				+ ", birthday=" + birthday + ", memo=" + memo + "]";
	}
	
}
