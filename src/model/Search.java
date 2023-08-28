package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Search {

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
	
	public static ObservableList<SecondSemesterTableContents> search_semester(String table_name, String search_item){
		conn = connectdb();
		ObservableList<SecondSemesterTableContents> list= FXCollections.observableArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from "+table_name+" where CourseCode like '%"+search_item+"%' or CourseName like '%"+search_item+"%' or LecturerName like '%"+search_item
					+"%'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new SecondSemesterTableContents(rs.getInt("ID"), rs.getInt("NumberOfStudents"), rs.getInt("StudentLevel"), rs.getString("CourseName"), rs.getString("Department"), 
						rs.getString("CourseCode"), rs.getString("LecturerName"), rs.getString("LecturerInitials"), rs.getString("Programme"), rs.getString("Groupings")));
			}	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Table couldn't Update\n"+e);
		}
		return list;
	}
	
	public static ObservableList<ScheduleTableContents> search_scheduled(String table_name, String search_item){
		conn = connectdb();
		ObservableList<ScheduleTableContents> list= FXCollections.observableArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from "+table_name+" where CourseCode like '%"+search_item+"%' or CourseName like '%"+search_item+"%' or LecturerName like '%"+search_item
					+"%'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new ScheduleTableContents(rs.getInt("ID"), rs.getInt("StudentLevel"), rs.getString("CourseCode"), rs.getString("Department"), 
						rs.getString("CourseName"), rs.getString("LecturerName"), rs.getString("LecturerInitials"), rs.getString("TimeAllocated"), rs.getString("RoomName")));
			}	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Table couldn't Update\n"+e);
		}
		return list;
	}
	
	public static ObservableList<RoomTableContents> search_room(String table_name, String search_item){
		conn = connectdb();
		ObservableList<RoomTableContents> list= FXCollections.observableArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from "+table_name+" where 	BuildingName like '%"+search_item+"%' or RoomName like '%"+search_item+"%'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new RoomTableContents(rs.getInt("ID"), rs.getInt("Size"), rs.getString("BuildingName"), rs.getString("RoomName")));
			}	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Table couldn't Update\n"+e);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
