package com.gawari._himanshu.soapcoursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Use Wizdler chrome plugin for Soap request and response 
//http://localhost:8080/ws/courses.wsdl ----> on this page use wizdler

@SpringBootApplication
public class SoapCourseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapCourseManagementApplication.class, args);
	}

}
