package model;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Database {
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
					+ " Timeslot");
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
	
	
//	public static void main(String[] args) {
//	}
	
}
