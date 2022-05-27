package com.flipkart;

import com.flipkart.dao.AdminDaoImplementation;
import com.flipkart.dao.StudentDaoImplementation;
import com.flipkart.dao.TeacherDAOImplementation;
import com.flipkart.dao.TeacherDAOInterface;
import com.flipkart.db.ConnectionUtil;
import com.flipkart.restApi.AdminRestApi;
import com.flipkart.restApi.StudentRestApi;
import com.flipkart.restApi.TeacherRestApi;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration>{

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
	
	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		
		ConnectionUtil connectionUtil = new ConnectionUtil(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
		
		AdminDaoImplementation adm = new AdminDaoImplementation(connectionUtil);
		AdminRestApi admapi = new AdminRestApi(adm);
		environment.jersey().register(admapi);
		
		StudentDaoImplementation stu = new StudentDaoImplementation(connectionUtil);
		StudentRestApi stumapi = new StudentRestApi(stu);
		environment.jersey().register(stumapi);
		
		TeacherDAOInterface teacherDAO = new TeacherDAOImplementation(connectionUtil);
		TeacherRestApi teachApi = new TeacherRestApi(teacherDAO);
		environment.jersey().register(teachApi);
		
		
//		ProductDAOJdbcImpl dao = new ProductDAOJdbcImpl(connectionUtil);
	
//		ProductResource resource = new ProductResource(dao);
		
//		environment.jersey().register(resource);
		
	}

}
