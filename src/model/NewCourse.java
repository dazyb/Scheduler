package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;

public class NewCourse {
	static String semester = "SecondSemester";
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
	
	public static void add(String courseName, String department, String courseCode, String lecturerName, String lecturerInitials, int numberOfStudents, int level, 
			String programme, String group) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into SecondSemester(CourseName,Department,CourseCode,LecturerName,LecturerInitials,NumberOfStudents,StudentLevel,Programme,Groupings) "
					+ "values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, courseName.toUpperCase());
			ps.setString(2, department.toUpperCase());
			ps.setString(3, courseCode.toUpperCase());
			ps.setString(4, lecturerName.toUpperCase());
			ps.setString(5, lecturerInitials.toUpperCase());
			ps.setInt(6, numberOfStudents);
			ps.setInt(7, level);
			ps.setString(8, programme.toUpperCase());
			ps.setString(9, group.toUpperCase());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Add Unsuccessful\n"+e.getMessage());
		}
	}
	
	public static void update(int id, String courseName, String department, String courseCode, String lecturerName, String lecturerInitials, int numberOfStudents, int level, 
			String programme, String group, int creditHours) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("update SecondSemester set CourseName='"+courseName.toUpperCase()+"',Department='"+department.toUpperCase()+"',CourseCode='"+courseCode.toUpperCase()+"',LecturerName='"+lecturerName.toUpperCase()+"',"
					+ "LecturerInitials='"+lecturerInitials.toUpperCase()+"',NumberOfStudents="+numberOfStudents+",StudentLevel="+level+",Programme='"+programme.toUpperCase()+"',Groupings='"+group.toUpperCase()+"', CreditHours="+creditHours+" where ID="+id);;
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Updated Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Update Unsuccessfully\n"+e.getMessage());
		}
	}
	
	public static void setItems(int ID, MFXTextField courseName, MFXTextField courseCode, MFXComboBox<String> dept, MFXTextField lname,  MFXTextField linitials,MFXTextField noStudents, MFXComboBox<Integer> level, MFXTextField programme, MFXTextField group, MFXComboBox<Integer> creditHours) {
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
					creditHours.setText(String.valueOf(rs.getInt("CreditHours")));
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Load Details Unsuccessful\n"+e.getMessage());
			}
		}
		
	}
	
}
