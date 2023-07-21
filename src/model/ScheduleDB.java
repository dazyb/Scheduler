package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class ScheduleDB {
	static Connection conn = null;
	public static Connection connectdb() {
		try {
			Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TimeTable;integratedSecurity=true");
//			System.out.println("YES");
			return conn; 
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.err.println(e.getMessage());
			
			return null;
		}
	}
	
	public static void new_schedule(String CourseName, String CourseCode, String LecturerName, String RoomName, String TimeAllocated, String Department, int level, String LecturerInitials) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into Schedule(CourseName,CourseCode,LecturerName,RoomName,TimeAllocated,Department,StudentLevel,LecturerInitials) "
					+ "values(?,?,?,?,?,?,?,?)");
			ps.setString(1, CourseName);
			ps.setString(2, CourseCode);
			ps.setString(3, LecturerName);
			ps.setString(4, RoomName);
			ps.setString(5, TimeAllocated);
			ps.setString(6, Department);
			ps.setInt(7, level);
			ps.setString(8, LecturerInitials);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	

	
	public static void displaySchedule(GridPane pane) {
		conn =connectdb();
		String query = "select * from Schedule where CourseName!='NULL' and Department='Mathematics' and StudentLevel=300";
		try {
			PreparedStatement ps =  conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			String[] position;
			String rowIndex;
			String columnIndex;
			while(rs.next()) {
				rowIndex = rs.getString("TimeAllocated").substring(0,3);
				columnIndex = rs.getString("TimeAllocated").substring(4,6);
				position= new String[] {rowIndex,columnIndex};
				display_gridPane(position, rs.getString("CourseCode"), rs.getString("LecturerInitials"), rs.getString("RoomName"),  pane);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		
	}
	
//	public static void main(String[] args) {
//		displaySchedule();
//	}

	
	//random methods
	static Label createNode(String courseCode, String lecturerInitials, String roomName) {
		Label label = new Label();
		label.setPrefSize(86, 76);
		label.setStyle("-fx-font-size: 14px;");
		label.setAlignment(Pos.CENTER);
		label.setText(courseCode+"\n   "+lecturerInitials+"\n"+roomName);
		return label;
	}
	
	static void display_gridPane(String[] position, String courseCode, String lecturerInitials, String roomName, GridPane pane) {
		//position =  [rowIndex,columnIndex]
		String rowIndex = position[0];
		String columnIndex = position[1];
	    HashMap<String,Integer> time = new HashMap<String,Integer>();
	    time.put("06", 1);
	    time.put("07", 2);
	    time.put("08", 3);
	    time.put("09", 4);
	    time.put("10", 5);
	    time.put("11", 6);
	    time.put("12", 7);
	    time.put("01", 8);
	    time.put("02", 9);
	    time.put("03", 10);
	    time.put("04", 11);
	    time.put("05", 12);
		switch (rowIndex) 
		{
			case "MON":
				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 1);
				break;
			case "TUE":
				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 2);
				break;
			case "WED":
				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 3);
				break;
			case "THU":
				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 4);
				break;
//			case "FRI":
//				pane.add(createNode(courseCode,lecturerName,roomName), 5, time.get(columnIndex));
//				break;		
		}
	}
	
}
