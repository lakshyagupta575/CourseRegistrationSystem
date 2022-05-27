package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Teacher;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.bean.Student;
import com.flipkart.db.ConnectionUtil;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminDaoImplementation implements AdminDaoInterface{
	
	ConnectionUtil connectionUtil;
	
    private volatile AdminDaoImplementation instance = null;
    public AdminDaoImplementation() {}
    public AdminDaoImplementation getInstance() {
        if (instance == null) {
            synchronized (AdminDaoImplementation.class) {
                instance = new AdminDaoImplementation();
            }
        }
        return instance;
    }
    
    public AdminDaoImplementation(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}
    
    
    @Override
    public boolean addCourse(Course course) {
    	try(Connection connection = connectionUtil.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE_QUERY);
            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setInt(3, course.getSeats());
            preparedStatement.setInt(4, course.getFee());

            int rows = preparedStatement.executeUpdate();
            
            if(rows == 1) {
            	return true;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
            
        return false;
            
    }
    
    @Override
    public String addStudent(Student student) {
    	String str = null;
        try(Connection connection = connectionUtil.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT_QUERY);
            preparedStatement.setString(1, student.getUserId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getDepartment());

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Student is registered");
            }
            str = student.getUserId();
        }
        catch(SQLException e)
        {
            System.out.println("Student with the ID exists. Try Again!!");
        }
        return str;
    }
    
    @Override
    public String addTeacher(Teacher teacher) {
    	String str = null;
    	try(Connection connection = connectionUtil.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_TEACHER_QUERY);
            preparedStatement.setString(1, teacher.getUserId());
            preparedStatement.setString(2, teacher.getName());
            preparedStatement.setString(3, teacher.getQualification());
            preparedStatement.setString(4, teacher.getDepartment());
            preparedStatement.setString(5, teacher.getCourseId());
            
            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Student is registered");
            }
            str = teacher.getUserId();
    	}
            catch(SQLException e)
            {
                System.out.println("Student with the ID exists. Try Again!!");
            }
            return str;
    }
    
//	@Override
//    public boolean addTeacher(Teacher teacher) {
//        boolean ok = true;
//        try(Connection con = connectionUtil.getConnection();){
//            Statement stmt = con.createStatement();
//            if(con==null)System.out.println("connection not established");
//            String sql = "insert into teacher values('"+ teacher.getUserId()+ "' ,' "+ teacher.getName()+ "' ,' "+ teacher.getQualification()+ "' ,' "+ teacher.getDepartment()+ "' ,' "+ teacher.getCourseId() + "')";
//            stmt.executeUpdate(sql);
//        } catch (SQLException e) {
//            ok = false;
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        return ok;
//    }

	/*
    @Override
    public boolean addCourse(Course course) {
        boolean ok = true;
        try{
            Connection con = connectionUtil.getConnection();
            Statement stmt = con.createStatement();
            if(con==null)System.out.println("connection not established");
            String sql = "insert into course values(" + course.getCourseId() + ", '" + course.getName() + ", '" + "10" + ", '" + "500" + "')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            ok = false;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ok;
    } */

}