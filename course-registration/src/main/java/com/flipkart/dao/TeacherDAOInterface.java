package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TeacherDAOInterface {

    public boolean registerCoursesWithDB(String professorId,String courseId) throws SQLException;
    public List<Student> viewEnrolledStudentsWithDB(String courseId) throws SQLException;
    public boolean provideGrade(String courseId,String studentId,String grade) throws SQLException;
    Optional<Course> viewCourses(String teacherId) throws SQLException, ClassNotFoundException;
}