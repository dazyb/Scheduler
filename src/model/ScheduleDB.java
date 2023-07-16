package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
	
	public static void new_schedule(String CourseName, String CourseCode, String LecturerName, String RoomName, String TimeAllocated) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into Schedule(CourseName,CourseCode,LecturerName,RoomName,TimeAllocated) "
					+ "values(?,?,?,?,?)");
			ps.setString(1, CourseName);
			ps.setString(2, CourseCode);
			ps.setString(3, LecturerName);
			ps.setString(4, RoomName);
			ps.setString(5, TimeAllocated);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	static Label createNode(String courseCode) {
		Label label = new Label();
		label.setPrefSize(90, 20);
		label.setStyle("-fx-font-size: 14px");
		label.setText(courseCode);
		label.setAlignment(Pos.CENTER);
		return label;
	}
	
	public static void displaySchedule(GridPane pane) {
		conn =connectdb();
//		HashMap<String,Integer> days  = new HashMap<String,Integer>();
		HashMap<String,Integer> time  = new HashMap<String,Integer>();
//		days.put("MO", 1);
//		days.put("TU", 2);
//		days.put("WE", 3);
//		days.put("TH", 4);
//		days.put("FR", 5);
		
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
		
		String courseCode,timeAllocated;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from Schedule where RoomName LIKE '%celt%' and CourseName !='NULL'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				courseCode = rs.getString("CourseCode");
				System.out.println(courseCode);
				timeAllocated = rs.getString("TimeAllocated");
//				System.out.println(time.get(timeAllocated.substring(4, 6)));
//				if(timeAllocated.substring(0, 3)=="MON") {
//					pane.add(createNode(rs.getString("CourseCode")), 1, time.get(timeAllocated.substring(4, 6)));
//				}else if(timeAllocated.substring(0, 3)=="TUE") {
//					pane.add(createNode(rs.getString("CourseCode")), 2, time.get(timeAllocated.substring(4, 6)));
//				}else {
//					JOptionPane.showMessageDialog(null, "agye ta");
//				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
}
