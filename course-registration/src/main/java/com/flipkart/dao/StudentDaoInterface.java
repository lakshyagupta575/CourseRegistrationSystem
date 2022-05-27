package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentCourse;
import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StudentDaoInterface
{

    Optional<Student> getStudent(String studentId) throws SQLException;

   int getTotalFees(String studentId) throws SQLException;
    
    void registerCourses(String studentId,String courseId) throws SQLException;

    ArrayList<Course> viewCourses(String studentId) throws SQLException;

    String viewGrades(String studentId,String courseId) throws SQLException;

}