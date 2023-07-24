package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomTableDB {
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
	
	public static ObservableList<RoomTableContents> getDatabaseTable(){
		conn=connectdb();
		ObservableList<RoomTableContents> list= FXCollections.observableArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from Rooms");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new RoomTableContents(rs.getInt("ID"),rs.getInt("Size"),rs.getString("BuildingName"),rs.getString("RoomName")));
			}	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Table couldn't Update\n"+e);
		}
		return list;
	}
	
	public static void add(String buildingName, String roomName, int capacity) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into Rooms(BuildingName,RoomName,Size) values(?,?,?)");
			ps.setString(1, buildingName);
			ps.setString(2, roomName);
			ps.setInt(3, capacity);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
}
