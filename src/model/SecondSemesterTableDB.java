package model;



import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SecondSemesterTableDB {
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
	
	public static void rooms(Timetable timetable) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from Rooms");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				timetable.addRoom(rs.getInt("ID"), rs.getString("RoomName"), rs.getInt("Size"));
			}
;		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	public static void lecturer(Timetable timetable) {
		conn = connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from Lecturer");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				timetable.addProfessor(rs.getInt("ID"), rs.getString("NameOfLecturer"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void course(Timetable timetable) {
		conn =  connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("Select ID,CourseCode,CourseName,ID from"
					+ " SecondSemester where NumberOfStudents > 0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				timetable.addModule(rs.getInt("ID"), rs.getString("CourseCode"), rs.getString("CourseName"), new int[] {rs.getInt("ID")});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void timeslot(Timetable timetable) {
		conn =  connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("Select ID,DayOfTheWeek,TimeAllocated from"
					+ " Timeslot where TimeAllocated='06:30 AM' or TimeAllocated='08:30 AM' or TimeAllocated='10:30 AM'or TimeAllocated='12:30 PM' or TimeAllocated='02:30 PM' or TimeAllocated='04:30 PM'"
					);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				timetable.addTimeslot(rs.getInt("ID"), rs.getString("DayOfTheWeek").concat(" ").concat(rs.getString("TimeAllocated")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void students(Timetable timetable) {
		conn =  connectdb();
		try {
			PreparedStatement ps = conn.prepareStatement("Select ID,NumberOFStudents,ID from"
					+ " SecondSemester where NumberOfStudents > 0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				timetable.addGroup(rs.getInt("ID"), rs.getInt("NumberOFStudents"), new int[] {rs.getInt("ID")});
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	public static String getdept(String courseCode, int ID) {
		conn =  connectdb();
		String dept = null;
		try {
			PreparedStatement ps = conn.prepareStatement("Select Department from"
					+ " SecondSemester where CourseCode='"+courseCode+"' and ID="+ID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				dept=rs.getString("Department");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return dept;
	}
	public static int getstudentLevel(String courseCode) {
		conn =  connectdb();
		int level = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("Select StudentLevel from"
					+ " SecondSemester where CourseCode='"+courseCode+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				level=rs.getInt("StudentLevel");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return level;
	}
	
	public static String getlecturerInitials(String courseCode, int ID) {
		conn =  connectdb();
		String initials = null;
		try {
			PreparedStatement ps = conn.prepareStatement("Select LecturerInitials from"
					+ " SecondSemester where CourseCode='"+courseCode+"' and ID="+ID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				initials=rs.getString("LecturerInitials");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return initials;
	}
	
	public static ArrayList<String> getDepartment() {
		conn = connectdb();
		ArrayList<String> dept = new ArrayList<String>();
		String query = "select distinct Department from SecondSemester";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				dept.add(rs.getString("Department"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dept;
	}
	
	public static void addNewCourse(String Department, String CourseName, String LecturerName, String LecturerInitials, int Level, String CourseCode, int noStudents, String Programme, String Groupings, int creditHours){
		conn=connectdb();
		try 
		{
			PreparedStatement prest=conn.prepareStatement("insert into SecondSemester(Department,CourseName, LecturerName,LecturerInitials,StudentLevel,CourseCode,NumberOfStudents,Programme,Groupings,CreditHours) values(?,?,?,?,?,?,?,?,?,?)");
			prest.setString(1, Department);
			prest.setString(2, CourseName);
			prest.setString(3, LecturerName);
			prest.setString(4, LecturerInitials);
			prest.setInt(5, Level);
			prest.setString(6, CourseCode);
			prest.setInt(7, noStudents);
			prest.setString(8, Programme);
			prest.setString(9, Groupings);
			prest.setInt(10, creditHours);
			prest.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Added Successfully");
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void updateCourse(String code, String cname, int noStudents,int ID, String lin, String lname, String groupings, String programme, String department, int level, int creditHours) {
		conn = connectdb(); 
		try {
			PreparedStatement ps = conn.prepareStatement("update SecondSemester set CourseName='"+cname+"', NumberOfStudents='"+noStudents+"' , CourseCode='"+code+"', LecturerInitials='"+lin+"', LecturerName='"+lname+"',"
					+ " Groupings='"+groupings+"', Programme='"+programme+"', Department='"+department+"', StudentLevel='"+level+"',CreditHours="+creditHours+"  where ID="+ID+"");
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Row Updated");
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.err.println(e.getMessage());
		}
	}
	
	public static ObservableList<SecondSemesterTableContents> getDatabaseTable(){
		conn=connectdb();
		ObservableList<SecondSemesterTableContents> list= FXCollections.observableArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from SecondSemester");
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
	
//	public static void main(String[] args) {
//		System.out.println(getDepartment());
//	}
	
}
