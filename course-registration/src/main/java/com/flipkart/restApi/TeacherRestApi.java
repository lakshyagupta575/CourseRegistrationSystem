package com.flipkart.restApi;

import com.flipkart.bean.Course;
import com.flipkart.bean.Teacher;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourse;
import com.flipkart.dao.TeacherDAOInterface;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/teacher/{teacherId}")
public class TeacherRestApi {

    TeacherDAOInterface teacherDAO;

    public TeacherRestApi(TeacherDAOInterface teacherDAO) {
        super();
        this.teacherDAO = teacherDAO;
    }

    @GET
    @Path("/course")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response viewCourses(@PathParam("teacherId") String teacherId) throws SQLException, ClassNotFoundException {
        return Response.ok(teacherDAO.viewCourses(teacherId)).build();
    }


    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response viewEnrolledStudents(@QueryParam("courseId") String courseId) throws SQLException {
        return Response.ok(teacherDAO.viewEnrolledStudentsWithDB(courseId)).build();
    }

    @PUT
    @Path("/course")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCourse(@PathParam("teacherId") String teacherId, Course c) {
        boolean updated = false;
        try {
            updated = teacherDAO.registerCoursesWithDB(teacherId, c.getCourseId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (updated) {
            return Response.noContent().build();
        } else {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
    }

//    @PUT
//    @Path("/course/{studentId}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response provideGrade (@PathParam("teacherId") String teacherId,
//                                  @HeaderParam("grade") String grade,
//                                  @PathParam("studentId") String studentId) {
//        try {
//            String courseId = teacherDAO.viewCourses(teacherId).get().getCourseId();
//            boolean updated = teacherDAO.provideGrade(courseId, studentId, grade);
//            if (updated) {
//                return Response.noContent().build();
//            } else {
//                throw new WebApplicationException(Status.NOT_FOUND);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
    
    @PUT
    @Path("/course/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response provideGrade (@PathParam("teacherId") String teacherId,
    							  StudentCourse obj,	
    							  @PathParam("studentId") String studentId) {
        try {
            String courseId = obj.getCourseId();
            boolean updated = teacherDAO.provideGrade(courseId, studentId, obj.getGrade());
            if (updated) {
                return Response.noContent().build();
            } else {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}