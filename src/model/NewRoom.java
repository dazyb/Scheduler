package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class NewRoom {
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
	
	public void add(String buildingName, String roomName, int capacity) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into Rooms(BuildingName,RoomName,Size) values(?,?,?)");
			ps.setString(1, buildingName);
			ps.setString(2, roomName);
			ps.setInt(3, capacity);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
}
