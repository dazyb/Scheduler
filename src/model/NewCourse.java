package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;

public class NewCourse {
	private String semester;
	static Connection conn = null;

//	public NewCourse(String semester) {
//		super();
//		this.semester = semester;
//	}
	
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
	
	public static void update(int id, String courseName, String department, String courseCode, String lecturerName, String lecturerInitials, int numberOfStudents, int level, 
			String programme, String group, String sem) {
		conn = connectdb();
		String query = "update "+sem+" set CourseName='"+courseName+"',Department='"+department+"',CourseCode='"+courseCode+"',LecturerName='"+lecturerName+"',"
				+ "LecturerInitials='"+lecturerInitials+"',NumberOfStudents="+numberOfStudents+",StudentLevel="+level+",Programmme='"+programme+"',Grouping='"+group+"' where ID="+id;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void setItems(int ID, MFXTextField courseName, MFXTextField courseCode, MFXComboBox<String> dept, MFXTextField lname,  MFXTextField linitials,MFXTextField noStudents, MFXComboBox<Integer> level, MFXTextField programme, MFXTextField group,
			MFXComboBox<String> sem) {
		conn = connectdb();
		if(ID>0) {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from SecondSemester where  ID="+ID);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					courseName.setText(rs.getString("CourseName"));
					courseCode.setText(rs.getString("CourseCode"));
					noStudents.setText(rs.getString("NumberOfStudents"));
					lname.setText(rs.getString("LecturerName"));
					dept.setText(rs.getString("Department"));
					linitials.setText(rs.getString("LecturerInitials"));
					level.setText(String.valueOf(rs.getInt("StudentLevel")));
					group.setText(rs.getString("Groupings"));
					programme.setText(rs.getString("Programme"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
			}
		}
		
	}
	
}
