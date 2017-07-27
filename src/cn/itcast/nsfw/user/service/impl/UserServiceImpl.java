package cn.itcast.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;




import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.core.exception.ServiceException;
import cn.itcast.core.util.ExcelUtil;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public void save(User entity) {
		userDao.save(entity);

	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);;
	}
	
	@Override
	public void deleteAll(Serializable[] ids) {
		userDao.deleteAll(ids);
	}
	
	@Override
	public List<User> findObjects() throws ServiceException {
		try {
//			int i=1/0;
		} catch (Exception e) {
			throw new ServiceException("业务请求失败。\n"+e.getMessage());
		}
		return userDao.findObjects();
	}

	@Override
	public User findObectsById(Serializable id) {
		return userDao.findObectsById(id);
	}

	@Override
	public void exportExcel(List<User > userList,
			ServletOutputStream outputStream) {
		ExcelUtil.exportExcel(userList, outputStream);
	}
	
	@Override
	public void importExcel(File userExcel) {
		System.out.println("UserServiceImpl.importExcel()");
		try {
			FileInputStream fileInputStream = new FileInputStream(userExcel);
			//1.读取工作簿
			//自动根据excel表格的版本来读取数据，返回一个接口
			Workbook workbook=WorkbookFactory.create(userExcel);
			//2.读取工作表
			Sheet sheet = workbook.getSheetAt(0); 
			//3.读取行
			//判断实际行数
			if(sheet.getPhysicalNumberOfRows()>2){
				//4.读取单元格
				User user=null;
				Row row=null;
				for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
					user=new User();
					row=sheet.getRow(i);
					String name=row.getCell(0).getStringCellValue();
					user.setName(name);
					String account=row.getCell(1).getStringCellValue();
					user.setAccount(account);
					String dept=row.getCell(2).getStringCellValue();
					user.setDept(dept);
					String gender=row.getCell(3).getStringCellValue();
					user.setGender("男".equals(gender));
					String mobile;
					try {
						mobile=row.getCell(4).getStringCellValue();
					} catch (Exception e) {
						//excel自动把数值转换成科学计数法
						double dMobile=row.getCell(4).getNumericCellValue();
						//用BigDecimal将科学计数法的数值转换成string
						mobile=BigDecimal.valueOf(dMobile).toString();
					}
					user.setMobile(mobile);
					String email=row.getCell(5).getStringCellValue();
					user.setEmail(email);
					Date birthday=new Date(row.getCell(6).getDateCellValue().getTime()) ;
					user.setBirthday(birthday);
					//设置其他
					user.setState(User.USER_STATE_VALID);
					user.setPassword("123456");
					save(user);
				}
			}
			workbook.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findUsersByAccountAndId(String account,String id) {
		return userDao.findUsersByAccountAndId(account, id); 
	}
	

}
