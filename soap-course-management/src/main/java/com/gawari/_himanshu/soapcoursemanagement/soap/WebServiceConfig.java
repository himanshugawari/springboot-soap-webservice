package com.gawari._himanshu.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring web service
@EnableWs
//spring configuration
@Configuration
public class WebServiceConfig {
	// MessageDispatcherServlet
	// ApplicationContext
	// url -> /ws/*

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet, "/ws/*");
	}

	// /ws/courses.wsdl
	// course-details.xsd
	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://_himanshu.gawari.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema);
		return definition;
	}

	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

	/*
	 * // MessageDispatcherServlet
	 * 
	 * @Bean public ServletRegistrationBean<MessageDispatcherServlet>
	 * messageDispatcherServlet( ApplicationContext applicationContext) {
	 * MessageDispatcherServlet messageDispatcherServlet = new
	 * MessageDispatcherServlet();
	 * messageDispatcherServlet.setApplicationContext(applicationContext);
	 * messageDispatcherServlet.setTransformWsdlLocations(true); return new
	 * ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet,
	 * "/we/*"); }
	 * 
	 * // /ws/courses.wsdl // CoursePort // Namespace -
	 * http://_himanshu.gawari.com/courses // course-details.xsd
	 * 
	 * @Bean(name = "courses") public DefaultWsdl11Definition
	 * defaultWsdl11Definition(XsdSchema schema) { DefaultWsdl11Definition
	 * definition = new DefaultWsdl11Definition();
	 * definition.setPortTypeName("CoursePort");
	 * definition.setTargetNamespace("http://_himanshu.gawari.com/courses");
	 * definition.setLocationUri("/ws"); definition.setSchema(schema); return
	 * definition; }
	 * 
	 * @Bean public XsdSchema courseSchema() { return new SimpleXsdSchema(new
	 * ClassPathResource("course-details.xsd")); }
	 */

}
