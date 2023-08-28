package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ScheduledExports {
	public static void export_courses(String excelFilePath) throws SQLException, IOException{

			//jdbc and exports path
			String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=TimeTable;integratedSecurity=true";
			String FilePath = excelFilePath;
			
			//connection
			Connection con = DriverManager.getConnection(jdbcURL);
			
			//sql query
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from Schedule");
			
			//excel 
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet =  workbook.createSheet("Schedule Data");
			
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("Course Code");
			row.createCell(1).setCellValue("Department");
			row.createCell(2).setCellValue("Student Level");
			row.createCell(3).setCellValue("Lecturer Name");
			row.createCell(4).setCellValue("Lecturer Initials");
			row.createCell(5).setCellValue("Venue");
			row.createCell(6).setCellValue("Time");

			
			int r = 1;
			while(rs.next()) {
				String dept = rs.getString("Department");
				String ccode =rs.getString("CourseCode");
				String lname = rs.getString("LecturerName");
				String linitials = rs.getString("LecturerInitials");
				int level = rs.getInt("StudentLevel");
				String venue =rs.getString("RoomName");
				String time =rs.getString("TimeAllocated");

				
				
				row = sheet.createRow(r++);
				
				row.createCell(0).setCellValue(ccode);
				row.createCell(1).setCellValue(dept);
				row.createCell(2).setCellValue(level);
				row.createCell(3).setCellValue(lname);
				row.createCell(4).setCellValue(linitials);
				row.createCell(5).setCellValue(venue);
				row.createCell(6).setCellValue(time);

			}
			
			FileOutputStream outputStream = new FileOutputStream(FilePath);
	        workbook.write(outputStream);
	        workbook.close();
	        con.close();
	        
	        JOptionPane.showMessageDialog(null, "Exported Successfully");
	}	
}
