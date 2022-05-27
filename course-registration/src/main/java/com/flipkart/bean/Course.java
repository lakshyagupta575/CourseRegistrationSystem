package com.flipkart.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
	
	private String courseId;
	private String name;
	// private String teacherId;
	private int seats;
	private int fee;
		
	public Course() {};
	
	public Course(String courseId, String name,int seats,int fee) {
		super();
		this.courseId = courseId;
		this.name=name;
		//this.teacherId=teacherId;
		this.seats=seats;
		this.fee=fee;
	}

	@JsonProperty
	public String getCourseId() {
		return courseId;
	}


	@JsonProperty
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}
	
//	public String getTeacherId() {
//		return teacherId;
//	}
//
//
//	public void setTeacherId(String teacherId) {
//		this.teacherId = teacherId;
//	}

	@JsonProperty
	public int getSeats() {
		return seats;
	}

	@JsonProperty
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	@JsonProperty
	public int getFee() {
		return fee;
	}

	@JsonProperty
	public void setFee(int fee) {
		this.fee = fee;
	}
	
}