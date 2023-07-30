package model;

import java.util.Objects;

public class ScheduleTableContents {
	private int ID,studentLevel;
	String courseCode,dept,courseName,lecturerName,lecturerInitials,timeAllocated,roomName;
	public ScheduleTableContents(int iD, int studentLevel, String courseCode, String dept, String courseName,
			String lecturerName, String lecturerInitials, String timeAllocated, String roomName) {
		super();
		ID = iD;
		this.studentLevel = studentLevel;
		this.courseCode = courseCode;
		this.dept = dept;
		this.courseName = courseName;
		this.lecturerName = lecturerName;
		this.lecturerInitials = lecturerInitials;
		this.timeAllocated = timeAllocated;
		this.roomName = roomName;
	}
	public int getID() {
		return ID;
	}
	public int getStudentLevel() {
		return studentLevel;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public String getDept() {
		return dept;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getLecturerName() {
		return lecturerName;
	}
	public String getLecturerInitials() {
		return lecturerInitials;
	}
	public String getTimeAllocated() {
		return timeAllocated;
	}
	public String getRoomName() {
		return roomName;
	}
	
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		ScheduleTableContents list_Controller = (ScheduleTableContents) o;
//		return getCourseName().equals(list_Controller.getCourseName());
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(getCourseName());
//	}
}
