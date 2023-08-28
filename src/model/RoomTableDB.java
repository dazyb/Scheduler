package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import io.github.palexdev.materialfx.controls.MFXTextField;
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
			ps.setString(1, buildingName.toUpperCase());
			ps.setString(2, roomName.toUpperCase());
			ps.setInt(3, capacity);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Add Unsuccessfully\n"+e.getMessage());
		}
	}
	
	public static void setItems(int ID, MFXTextField bname, MFXTextField rname, MFXTextField capacity) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from Rooms where ID="+ID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bname.setText(rs.getString("BuildingName"));
				rname.setText(rs.getString("RoomName"));
				capacity.setText(String.valueOf(rs.getInt("Size")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Load Details Unsuccessful\n"+e.getMessage());
		}
	}
	
	public static void update(int ID, String bname, String rname, int capacity) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("update Rooms set BuildingName='"+bname.toUpperCase()+"', RoomName='"+rname.toUpperCase()+"', Size="+capacity+" where ID="+ID);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Updated Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Update Unsuccessfully\n"+e.getMessage());
		}
	}
	
	public static void getInnerJoin(){
		conn = connectdb();
		try {
			PreparedStatement ps=conn.prepareStatement("select Schedule.RoomName from Schedule "
					+ "inner join Rooms on Schedule.RoomName=Rooms.RoomName");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				assignAvailability(rs.getString("RoomName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	public static void assignAvailability(String roomName) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("update Rooms set Status='ASSIGNED' where RoomName='"+roomName+"'");
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		RoomTableDB.getInnerJoin();
	}

}
