package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Teacher;
import com.flipkart.bean.Student;
import com.flipkart.db.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherDAOImplementation implements TeacherDAOInterface {

    ConnectionUtil connectionUtil;
    
    public TeacherDAOImplementation(ConnectionUtil connectionUtil) {
		// TODO Auto-generated constructor stub
    	this.connectionUtil = connectionUtil;
	}

	@Override
	public boolean registerCoursesWithDB(String teacherId, String courseId) throws SQLException {
		// TODO Auto-generated method stub
		int res = 0;
		try(Connection connection = connectionUtil.getConnection();) {
			String sql = "update teacher set courseId = ? where userId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseId);
			preparedStatement.setString(2, teacherId);
		    res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res == 1;
	}


	@Override
	public List<Student> viewEnrolledStudentsWithDB(String courseId) throws SQLException {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<Student>();
		try(Connection connection = connectionUtil.getConnection();) {
			//this query could break, check later
			String sql = "select * from student where userId in (select studentId from student_course"
					+ " where courseId = ?)";
			//
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseId);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()) {
		    	Student student = new Student(rs.getString(1), rs.getString(2), rs.getString(3));
		    	students.add(student);
		    }
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return students;
	}


	@Override
	public boolean provideGrade(String courseId, String studentId, String grade) throws SQLException {
		// TODO Auto-generated method stub
		int res = 0;
		try(Connection connection = connectionUtil.getConnection();) {
			String sql = "update student_course set grade = ? where studentId = ? and courseId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, grade);
			preparedStatement.setString(2, studentId);
			preparedStatement.setString(3, courseId);
		    res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res == 1;
	}


	@Override
	public Optional<Course> viewCourses(String teacherId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Optional<Course> opt = Optional.empty();
		try(Connection connection = connectionUtil.getConnection();) {
			String sql = "select * from course where courseId in (select courseId from teacher where userId = ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherId);
		    ResultSet rs = preparedStatement.executeQuery();
		    if (rs.next()) {
		    	Course course = new Course(rs.getString(1), rs.getString(2),
		    			rs.getInt(3), rs.getInt(4));
		    	opt = Optional.of(course);
		    }
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return opt;
	}
}