package com.flipkart.bean;

public class StudentCourse {
	private String studentId, courseId, grade;

	public StudentCourse() {
		
	}
	
	public StudentCourse(String studentId, String courseId, String grade) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}