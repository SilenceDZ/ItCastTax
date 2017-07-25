package cn.itcast.core.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.itcast.nsfw.user.entity.User;

public class ExcelUtil {
	/**
	 *Title:exportExcel
	 *Description:导出用户列表到excel
	 *@param userList
	 *@param outputStream 输出流
	 *Throws
	 */
	public static void exportExcel(List<User > userList,
			ServletOutputStream outputStream) {
		try {
			//1创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 1.1创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
			// 1.2创建头标题行样式并创建字体  
			HSSFCellStyle headStyle=createCellStyle(workbook, (short)16);
			//1.3创建标题样式
			HSSFCellStyle titleStyle = createCellStyle(workbook, (short)13);
			//2.创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			//2.1加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			sheet.setDefaultColumnWidth(20);
			//3、创建行
			//3.1创建头标题并写入头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			cell1.setCellStyle(headStyle);
			cell1.setCellValue("用户列表"); 
			//3.2创建列标题并写入列标题
			HSSFRow row2 = sheet.createRow(1);
			String []titles={"用户名","账号","所属部门","性别","邮箱"};
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell2 = row2.createCell(i);
				cell2.setCellValue(titles[i].toString());
				cell2.setCellStyle(titleStyle);
			}
			//4、创建单元格，写入用户数据到excel
			if(userList!=null &&userList.size()>0){
				for (int j = 0; j < userList.size(); j++) {
					HSSFRow row=sheet.createRow(j+2);
					row.createCell(0).setCellValue(userList.get(j).getName());
					row.createCell(1).setCellValue(userList.get(j).getAccount());
					row.createCell(2).setCellValue(userList.get(j).getDept());
					row.createCell(3).setCellValue(userList.get(j).isGender()?"男":"女");
					row.createCell(4).setCellValue(userList.get(j).getEmail());
				}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void importExcel(File userExcel){
		
	}
	/**
	 *Title:createCellStyle
	 *Description:创建单元格演示
	 *@param workbook 工作簿
	 *@param fontSize 字体大小
	 *@return 单元格样式
	 *Throws
	 */
	private static  HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short fontSize){
		
		// 1.2创建头标题行样式并创建字体
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontSize);
		// 在样式中加载字体
		style.setFont(font);
		return style;
	}
	
}
