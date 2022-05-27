package com.flipkart.dao;

import java.util.List;
import java.util.Optional;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Teacher;
import com.flipkart.bean.Student;

public interface AdminDaoInterface {
    
	default String addTeacher(Teacher teacher) {
        return "";
    }
    
    default String addStudent(Student student) {
    	return "";
    }
    
    boolean addCourse(Course course);

}