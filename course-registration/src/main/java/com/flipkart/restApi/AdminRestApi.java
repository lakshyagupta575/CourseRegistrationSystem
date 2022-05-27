package com.flipkart.restApi;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Teacher;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/admin")
public class AdminRestApi {
	
	AdminDaoInterface adminDao;
	
	
	
	
	public AdminRestApi(AdminDaoInterface adminDao) {
		super();
		this.adminDao = adminDao;
	}

	
//	@GET
//	@Path("/{id}")
//	public Response getAdmin(@PathParam("id") String userId, @QueryParam("pass") String pass) throws ClassNotFoundException, SQLException {
//		Optional<Admin> admin = adminDao.validateCredentials(userId, pass);
//		if(admin.isPresent()) {
//			return Response.ok(admin.get()).build();
//		}
//		else {
//			throw new WebApplicationException(404);
//		}
//	}
	
	
	
	@POST
	@Path("/addStudent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(Student s) {
		String ok = adminDao.addStudent(s);
		if(ok != null) {
			return Response.status(201).entity(s).build();
		}
		else {
			throw new WebApplicationException();
		}
	}

	@POST
	@Path("/addTeacher")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTeacher(Teacher t) {
		String ok = adminDao.addTeacher(t);
		//exception if return false
		if(ok != null) {
			return Response.status(201).entity(t).build();
		}
		else {
			throw new WebApplicationException(400);
		}
	}
	
	
//	@POST
//	@Path("/addCourse")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addCourse(Course c, @QueryParam("teacherId") String t) {
//		
//		boolean ok = adminDao.addCourse(c, t);
//		//check if return false or not
//		if(ok) {
//			return Response.created(URI.create("/course/")).entity(c).build();
//		}
//		else {
//			throw new WebApplicationException(400);
//		}
//		
//	}
	
	@POST
	@Path("/addCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Course c) {
		
		boolean ok = adminDao.addCourse(c);
		//check if return false or not
		if(ok) {
			return Response.created(URI.create("/course/")).entity(c).build();
		}
		else {
			throw new WebApplicationException(400);
		}
		
	}
	
//	@DELETE
//	@Path("/dropCourse/{id}")
//	public Response dropCourse(@PathParam("id") String courseId) {
//		boolean deleted = adminDao.dropCourse(courseId);
//		if(deleted) {
//			return Response.noContent().build();
//		}
//		else {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//		
//	}
	
//	@POST
//	@Path("/approveStudent/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response approveStudent(@PathParam("id") String studentId) {
//		boolean ok = adminDao.approveStudent(studentId);
//		if(ok) {
//			return Response.status(201).entity("approved").build();
//		}
//		else {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}
//		
//	}
//	
//	
////	@GET
////	@Path("/approveStudents")
////	@Produces(MediaType.APPLICATION_JSON)
////	public List<Student> approveStudents(){
////		return adminDao.approveStudents();
////	}
	
	
}
