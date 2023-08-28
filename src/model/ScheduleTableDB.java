package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

public class ScheduleTableDB {
	static Connection conn = null;
	//create connection to database
	public static Connection connectdb() {
		try {
			Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TimeTable;integratedSecurity=true");
			return conn; 
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	//truncate table(makes room for new Schedule)
	public static void truncate() {
		conn = connectdb();
		try {
			Statement s = conn.createStatement();
			s.executeUpdate("truncate table Schedule");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	//get schedules items from database
	public static ObservableList<ScheduleTableContents> getScheduleTable(){
		conn=connectdb();
		ObservableList<ScheduleTableContents> list= FXCollections.observableArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from Schedule");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new ScheduleTableContents(rs.getInt("ID"), rs.getInt("StudentLevel"), rs.getString("CourseCode"), rs.getString("Department"),
						rs.getString("courseName"), rs.getString("LecturerName"), rs.getString("LecturerInitials"), rs.getString("TimeAllocated"), rs.getString("RoomName")));
			}	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Table couldn't Update\n"+e);
		}
		return list;
	}
	//schedule items
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
	//display schedule items in a gridpane
//	public static void displaySchedule(String dept, int level, StackPane pane) {
//		conn =connectdb();
//		ArrayList<String> lname = new ArrayList<String>();
//		String query = "select * from Schedule where Department='"+dept+"' and StudentLevel="+level;
//		try {
//			PreparedStatement ps =  conn.prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			String[] position;
//			String rowIndex;
//			String columnIndex;
//			while(rs.next()) {
//				rowIndex = rs.getString("TimeAllocated").substring(0,3);
//				columnIndex = rs.getString("TimeAllocated").substring(4,6);
//				lname.add(rs.getString("LecturerName"));
//				position= new String[] {rowIndex,columnIndex};
//				createGridPane(pane,position,rs.getString("CourseCode"),rs.getString("LecturerInitials"),rs.getString("RoomName"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.err.println(e.getMessage());
//		}
//	}
//	

	//random methods
	//create a label as a child in the grid pane
	static Label createNode(String courseCode, String lecturerInitials, String roomName) {
		Label label = new Label();
		label.setBackground(Background.fill(Paint.valueOf("gray")));
		label.setPrefSize(172, 76);
		label.setStyle("-fx-font-size: 14px;");
		label.setAlignment(Pos.CENTER);
		label.setText(courseCode+"\n"+roomName+"\n"+lecturerInitials);
		return label;
	}
	//header labels(rows)
	static Label headerRows(String text) {
		Label label =new Label();
		label.setPrefSize(86, 76);
		label.setText(text);
		label.setAlignment(Pos.CENTER);
		label.setStyle(" -fx-font-size: 14px;");
		return label;
	}
	//header labels(columns)
	static Label headerColumns(String text) {
		Label label = new Label();
		label.setPrefSize(86, 76);
		label.setText(text);
		label.setAlignment(Pos.CENTER);
		label.setStyle(" -fx-font-size: 14px;");
		return label;
	}
	
	//display scheduled timetable from database to grid pane
	public static void createGridPane(StackPane pane,String dept, int level) {
		GridPane grid = new GridPane();
		grid.setId("grid");
		grid.setGridLinesVisible(true);
		//grid columns
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		ColumnConstraints column6 = new ColumnConstraints();
		ColumnConstraints column7 = new ColumnConstraints();
		ColumnConstraints column8 = new ColumnConstraints();
		ColumnConstraints column9 = new ColumnConstraints();
		ColumnConstraints column10 = new ColumnConstraints();
		ColumnConstraints column11 = new ColumnConstraints();
		ColumnConstraints column12 = new ColumnConstraints();
		ColumnConstraints column13 = new ColumnConstraints();
		column1.setPercentWidth(-1);
		column2.setPercentWidth(-1);
		column3.setPercentWidth(-1);
		column4.setPercentWidth(-1);
		column5.setPercentWidth(-1);
		column6.setPercentWidth(-1);
		column7.setPercentWidth(-1);
		column8.setPercentWidth(-1);
		column9.setPercentWidth(-1);
		column10.setPercentWidth(-1);
		column11.setPercentWidth(-1);
		column12.setPercentWidth(-1);
		column13.setPercentWidth(-1);
		//grid rows
		RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		RowConstraints row3 = new RowConstraints();
		RowConstraints row4 = new RowConstraints();
		RowConstraints row5 = new RowConstraints();
		RowConstraints row6 = new RowConstraints();
		row1.setPercentHeight(-1);
		row2.setPercentHeight(-1);
		row3.setPercentHeight(-1);
		row4.setPercentHeight(-1);
		row5.setPercentHeight(-1);
		row6.setPercentHeight(-1);
		//add rows and columns to the grid
		grid.getColumnConstraints().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column13);
		grid.getRowConstraints().addAll(row1,row2,row3,row4,row5,row6);
		//add headers(rows)
		grid.add(headerRows("Monday"), 0, 1);
		grid.add(headerRows("Tuesday"), 0, 2);
		grid.add(headerRows("Wednesday"), 0, 3);
		grid.add(headerRows("Thursday"), 0, 4);
		grid.add(headerRows("Friday"), 0, 5);
		//add headers(columns)
		grid.add(headerColumns("06:30 AM\n07:30 AM"), 1, 0);
		grid.add(headerColumns("07:30 AM\n08:30 AM"), 2, 0);
		grid.add(headerColumns("08:30 AM\n09:30 AM"), 3, 0);
		grid.add(headerColumns("09:30 AM\n10:30 AM"), 4, 0);
		grid.add(headerColumns("10:30 AM\n11:30 AM"), 5, 0);
		grid.add(headerColumns("11:30 AM\n12:30 PM"), 6, 0);
		grid.add(headerColumns("12:30 PM\n01:30 PM"), 7, 0);
		grid.add(headerColumns("01:30 PM\n02:30 PM"), 8, 0);
		grid.add(headerColumns("02:30 PM\n03:30 PM"), 9, 0);
		grid.add(headerColumns("03:30 PM\n04:30 PM"), 10, 0);
		grid.add(headerColumns("04:30 PM\n05:30 PM"), 11, 0);
		grid.add(headerColumns("05:30 PM\n06:30 PM"), 12, 0);
		
		conn =connectdb();
		ArrayList<String> lname = new ArrayList<String>();
		String query = "select * from Schedule where Department='"+dept.toUpperCase()+"' and StudentLevel="+level;
		try {
			PreparedStatement ps =  conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			String[] position;
			String rowIndex;
			String columnIndex;
			while(rs.next()) {
				rowIndex = rs.getString("TimeAllocated").substring(0,3);
				columnIndex = rs.getString("TimeAllocated").substring(4,6);
				lname.add(rs.getString("LecturerName"));
				position= new String[] {rowIndex,columnIndex};
				//fill cells
				//position =  [rowIndex,columnIndex]
						String row_Index = position[0];
						String column_Index = position[1];
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
						switch (row_Index) 
						{
							case "MON":
								grid.add(createNode(rs.getString("CourseCode"),rs.getString("LecturerInitials"),rs.getString("RoomName")), time.get(column_Index), 1, time.get(column_Index)+1,1);
								break;
							case "TUE":
								grid.add(createNode(rs.getString("CourseCode"),rs.getString("LecturerInitials"),rs.getString("RoomName")), time.get(column_Index), 2, time.get(column_Index)+1,1);
								break;
							case "WED":
								grid.add(createNode(rs.getString("CourseCode"),rs.getString("LecturerInitials"),rs.getString("RoomName")), time.get(column_Index), 3, time.get(column_Index)+1,1);
								break;
							case "THU":
								grid.add(createNode(rs.getString("CourseCode"),rs.getString("LecturerInitials"),rs.getString("RoomName")), time.get(column_Index), 4, time.get(column_Index)+1,1);
								break;
							case "FRI":
								grid.add(createNode(rs.getString("CourseCode"),rs.getString("LecturerInitials"),rs.getString("RoomName")), time.get(column_Index), 5, time.get(column_Index)+1,1);
								break;		
						}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		pane.getChildren().add(grid);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	static void display_gridPane(String[] position, String courseCode, String lecturerInitials, String roomName, GridPane pane) {
//		//position =  [rowIndex,columnIndex]
//		String rowIndex = position[0];
//		String columnIndex = position[1];
//	    HashMap<String,Integer> time = new HashMap<String,Integer>();
//	    time.put("06", 1);
//	    time.put("07", 2);
//	    time.put("08", 3);
//	    time.put("09", 4);
//	    time.put("10", 5);
//	    time.put("11", 6);
//	    time.put("12", 7);
//	    time.put("01", 8);
//	    time.put("02", 9);
//	    time.put("03", 10);
//	    time.put("04", 11);
//	    time.put("05", 12);
//		switch (rowIndex) 
//		{
//			case "MON":
//				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 1);
//				break;
//			case "TUE":
//				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 2);
//				break;
//			case "WED":
//				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 3);
//				break;
//			case "THU":
//				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 4);
//				break;
//			case "FRI":
//				pane.add(createNode(courseCode,lecturerInitials,roomName), time.get(columnIndex), 5);
//				break;		
//		}
//	}
//	main method for class test runs	
//	public static void main(String[] args) {
//	
//}


	
}
