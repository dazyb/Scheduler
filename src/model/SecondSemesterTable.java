package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Table {
	static Connection conn = null;
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
	
	public static ObservableList<TContents> getDatabaseTable(){
		conn=connectdb();
		ObservableList<TContents> list= FXCollections.observableArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from SecondSemester");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new TContents(rs.getInt("ID"), rs.getInt("NumberOfStudents"), rs.getInt("StudentLevel"), rs.getString("CourseName"), rs.getString("Department"),
						rs.getString("CourseCode"), rs.getString("LecturerName"), rs.getString("LecturerInitials"), rs.getString("Programme"), rs.getString("Groupings")));
			}	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Table couldn't Update\n"+e);
		}
		return list;
	}
}
