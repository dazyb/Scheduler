package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class NewCourse {
	private String semester;
	static Connection conn = null;

	public NewCourse(String semester) {
		super();
		this.semester = semester;
	}
	
	public static Connection connectdb() {
		try {
			Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TimeTable;integratedSecurity=true");
			return conn; 
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public void add(String courseName, String department, String courseCode, String lecturerName, String lecturerInitials, int numberOfStudents, int level, 
			String programme, String group) {
		conn = connectdb();
		String current_semester = this.semester;
		try {
			PreparedStatement ps = conn.prepareStatement("insert into "+current_semester+"(CourseName,Department,CourseCode,LecturerName,LecturerInitials,NumberOfStudents,StudentLevel,Programme,Groupings "
					+ "values (?,?,?,?,?,?,?,?,?))");
			ps.setString(1, courseName);
			ps.setString(2, department);
			ps.setString(3, courseCode);
			ps.setString(4, lecturerName);
			ps.setString(5, lecturerInitials);
			ps.setInt(6, numberOfStudents);
			ps.setInt(7, level);
			ps.setString(8, programme);
			ps.setString(9, group);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	public void update(int id, String courseName, String department, String courseCode, String lecturerName, String lecturerInitials, int numberOfStudents, int level, 
			String programme, String group) {
		conn = connectdb();
		String query = "update "+this.semester+" set CourseName='"+courseName+"',Departmenr='"+department+"',CourseCode='"+courseCode+"',LecturerName='"+lecturerName+"',"
				+ "LecturerInitials='"+lecturerInitials+"',NumberOfStudents="+numberOfStudents+",StudentLevel="+level+",Programmme='"+programme+"',Grouping='"+group+"' where ID="+id;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
