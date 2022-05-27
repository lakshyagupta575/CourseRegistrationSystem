package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourse;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.db.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentDaoImplementation implements StudentDaoInterface {

	ConnectionUtil connectionUtil;
	
	
    public static StudentDaoInterface getInstance() {
        return null;
    }
    
    public StudentDaoImplementation(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

    @Override
    public Optional<Student> getStudent(String studentId) throws SQLException {
    	Optional<Student> opt = Optional.empty();
        Connection conn = connectionUtil.getConnection();
        // String sql = "SELECT * FROM student where userId = " + studentId;
        String sql = "SELECT * FROM student where userId = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, studentId);
        ResultSet rs = statement.executeQuery();
        
        if(rs.next())
        {
            //Student student1=new Student(studentId)
            Student student=new Student(rs.getString(1), rs.getString(2), rs.getString(3));
            opt = Optional.of(student);
        }
        return opt;
    }

    
 
//    public int getTotalFees(String studentId) throws SQLException {
//        Connection conn = connectionUtil.getConnection();
//        String sql = "SELECT COUNT(*) from student_course where studentId ="+studentId;
//        PreparedStatement statement = conn.prepareStatement(sql);
//        ResultSet rs = statement.executeQuery();
//        rs.next();
//        int value=rs.getInt(1);
//        value *= 500;
//         return value;
//    }
    
    public int getTotalFees(String studentId) throws SQLException {
    	String str = null;
    	try(Connection connection = connectionUtil.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_FEE);
            preparedStatement.setString(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            return rs.getInt(1)*500;
          
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        return 0;
    }
   
    @Override
    public void registerCourses(String studentId, String courseId) throws SQLException {
        Connection connection = connectionUtil.getConnection();
        String sql = "INSERT INTO student_course VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, studentId);
        statement.setString(2, courseId);
        statement.setString(3, "NA");
        statement.executeUpdate();
    }

    @Override
    public ArrayList<Course> viewCourses(String studentId) throws SQLException {
        ArrayList<Course> courses=new ArrayList<Course>();
        Connection conn = connectionUtil.getConnection();
        String sql = "SELECT * FROM course where courseId in (select courseId from student_course where studentId = ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, studentId);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
          Course course= new Course();
          course.setCourseId(rs.getString(1));
          course.setName(rs.getString(2));
          // course.setTeacherId(rs.getString(3));
          course.setSeats(rs.getInt(3));
          course.setFee(rs.getInt(4));
          courses.add(course);
        }
        return courses;
    }

 @Override
    public String viewGrades(String studentId, String courseId) throws SQLException {
   
        Connection conn = connectionUtil.getConnection();
        String sql = "SELECT grade FROM student_course where studentId = ? and courseId = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, studentId);
        statement.setString(2, courseId);
        ResultSet rs = statement.executeQuery();
        rs.next();
         String grade=rs.getString(1);
        return grade;
    }
}