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

public class CoursesExport {
	public static void export_courses(String excelFilePath){
		try {
			//jdbc and exports path
			String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=TimeTable;integratedSecurity=true";
			String FilePath = excelFilePath;
			
			//connection
			Connection con = DriverManager.getConnection(jdbcURL);
			
			//sql query
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from SecondSemester");
			
			//excel 
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet =  workbook.createSheet("Courses Data");
			
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("Course Name");
			row.createCell(1).setCellValue("Department");
			row.createCell(2).setCellValue("Course Code");
			row.createCell(3).setCellValue("Lecturer Name");
			row.createCell(4).setCellValue("Lecturer Initials");
			row.createCell(5).setCellValue("Number Of Students");
			row.createCell(6).setCellValue("Student Level");
			row.createCell(7).setCellValue("Programme");
			row.createCell(8).setCellValue("Groupings");
			row.createCell(9).setCellValue("Credit Hours");
			
			int r = 1;
			while(rs.next()) {
				String cname = rs.getString("CourseName");
				String dept = rs.getString("Department");
				String ccode =rs.getString("CourseCode");
				String lname = rs.getString("LecturerName");
				String linitials = rs.getString("LecturerInitials");
				int nStudents = rs.getInt("NumberOfStudents");
				int level = rs.getInt("StudentLevel");
				String prog =rs.getString("Programme");
				String group =rs.getString("Groupings");
				String creditHours =rs.getString("CreditHours");
				
				
				row = sheet.createRow(r++);
				
				row.createCell(0).setCellValue(cname);
				row.createCell(1).setCellValue(dept);
				row.createCell(2).setCellValue(ccode);
				row.createCell(3).setCellValue(lname);
				row.createCell(4).setCellValue(linitials);
				row.createCell(5).setCellValue(nStudents);
				row.createCell(6).setCellValue(level);
				row.createCell(7).setCellValue(prog);
				row.createCell(8).setCellValue(group);
				row.createCell(9).setCellValue(creditHours);	
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
