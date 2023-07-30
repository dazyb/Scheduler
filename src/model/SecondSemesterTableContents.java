package model;

import java.util.Objects;

public class SecondSemesterTableContents {
	private int ID,numberOfStudents,level;
	private String CourseName,Department,CourseCode,LecturerName,LecturerInitials,Programme,Groupings;
	public SecondSemesterTableContents(int iD, int numberOfStudents, int level, String courseName, String department, String courseCode,
			String lecturerName, String lecturerInitials, String programme, String groupings) {
		super();
		ID = iD;
		this.numberOfStudents = numberOfStudents;
		this.level = level;
		CourseName = courseName;
		Department = department;
		CourseCode = courseCode;
		LecturerName = lecturerName;
		LecturerInitials = lecturerInitials;
		Programme = programme;
		Groupings = groupings;
	}
	public int getID() {
		return ID;
	}
	public int getNumberOfStudents() {
		return numberOfStudents;
	}
	public int getLevel() {
		return level;
	}
	public String getCourseName() {
		return CourseName;
	}
	public String getDepartment() {
		return Department;
	}
	public String getCourseCode() {
		return CourseCode;
	}
	public String getLecturerName() {
		return LecturerName;
	}
	public String getLecturerInitials() {
		return LecturerInitials;
	}
	public String getProgramme() {
		return Programme;
	}
	public String getGroupings() {
		return Groupings;
	}
	
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		SecondSemesterTableContents list_Controller = (SecondSemesterTableContents) o;
//		return getCourseName().equals(list_Controller.getCourseName());
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(getCourseName());
//	}
}
