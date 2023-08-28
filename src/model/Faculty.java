package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Faculty {
	
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
	
	public static ObservableList<String> get_Department(String value){
		conn = connectdb();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from College where Faculty_SchoolName='"+value+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("Department"));
			}
		} catch (Exception e) { 
			// TODO: handle exception
		}
		return list;
	}
	
	public static ObservableList<String> get_Department(){
		conn = connectdb();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from College");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("Department"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static ObservableList<String> get_College(){
		conn = connectdb();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("Select distinct CollegeName from College");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("CollegeName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static ObservableList<String> get_Faculty(){
		conn = connectdb();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("Select distinct Faculty_SchoolName from College");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("Faculty_SchoolName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static ObservableList<String> get_Faculty(String value){
		conn = connectdb();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("Select distinct Faculty_SchoolName from College where CollegeName='"+value+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("Faculty_SchoolName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	
	
}
