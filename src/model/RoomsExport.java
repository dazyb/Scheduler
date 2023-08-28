package model;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RoomsExport {
	public static void export_courses(String excelFilePath){
		try {
			//jdbc and exports path
			String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=TimeTable;integratedSecurity=true";
			String FilePath = excelFilePath;
			
			//connection
			Connection con = DriverManager.getConnection(jdbcURL);
			
			//sql query
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from Rooms");
			
			//excel 
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet =  workbook.createSheet("Rooms Data");
			
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("Building Name");
			row.createCell(1).setCellValue("Room Name");
			row.createCell(2).setCellValue("Capacity");
			row.createCell(3).setCellValue("Availabilty");


			
			int r = 1;
			while(rs.next()) {
				String bname = rs.getString("BuildingName");
				String rname =rs.getString("RoomName");
				int cap = rs.getInt("Size");
				String avail = rs.getString("Status");


				
				
				row = sheet.createRow(r++);
				
				row.createCell(0).setCellValue(bname);
				row.createCell(1).setCellValue(rname);
				row.createCell(2).setCellValue(cap);
				row.createCell(3).setCellValue(avail);

			}
			
			FileOutputStream outputStream = new FileOutputStream(FilePath);
	        workbook.write(outputStream);
	        workbook.close();
	        con.close();
	        
	        JOptionPane.showMessageDialog(null, "Exported Successfully");
		
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,"An error has occurred, Check file path\n"+e.getMessage());
		}
	}
}
