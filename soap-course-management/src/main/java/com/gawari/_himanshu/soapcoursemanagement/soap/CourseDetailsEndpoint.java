package com.gawari._himanshu.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.gawari._himanshu.courses.CourseDetails;
import com.gawari._himanshu.courses.DeleteCourseDetailsRequest;
import com.gawari._himanshu.courses.DeleteCourseDetailsResponse;
import com.gawari._himanshu.courses.GetAllCourseDetailsRequest;
import com.gawari._himanshu.courses.GetAllCourseDetailsResponse;
import com.gawari._himanshu.courses.GetCourseDetailsRequest;
import com.gawari._himanshu.courses.GetCourseDetailsResponse;
import com.gawari._himanshu.soapcoursemanagement.soap.bean.Course;
import com.gawari._himanshu.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.gawari._himanshu.soapcoursemanagement.soap.service.CourseDetailsService;
import com.gawari._himanshu.soapcoursemanagement.soap.service.CourseDetailsService.Status;

//method
//input - GetCourseDetailsRequest
//output - GetCourseDetailsResponse
//http://_himanshu.gawari.com/courses

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService courseDetailsService;

	@PayloadRoot(namespace = "http://_himanshu.gawari.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

		Course course = courseDetailsService.findById(request.getId());
		if (course == null)
			throw new CourseNotFoundException("Invalid Course Id " + request.getId());
		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();

		courseDetails.setId(course.getId());

		courseDetails.setName(course.getName());

		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	@PayloadRoot(namespace = "http://_himanshu.gawari.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = courseDetailsService.findAll();

		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://_himanshu.gawari.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = courseDetailsService.deleteById(request.getId());

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}

	private com.gawari._himanshu.courses.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.gawari._himanshu.courses.Status.FAILURE;
		return com.gawari._himanshu.courses.Status.SUCCESS;
	}

	// 2
	/*
	 * @PayloadRoot(namespace = "http://_himanshu.gawari.com/courses", localPart =
	 * "GetCourseDetailsRequest")
	 * 
	 * @ResponsePayload public GetCourseDetailsResponse
	 * processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request)
	 * {
	 * 
	 * Course course = courseDetailsService.findById(request.getId());
	 * 
	 * return mapCourse(course); }
	 * 
	 * private GetCourseDetailsResponse mapCourse(Course course) {
	 * GetCourseDetailsResponse response = new GetCourseDetailsResponse();
	 * 
	 * CourseDetails courseDetails = new CourseDetails();
	 * 
	 * courseDetails.setId(course.getId());
	 * 
	 * courseDetails.setName(course.getName());
	 * 
	 * courseDetails.setDescription(course.getDescription());
	 * 
	 * response.setCourseDetails(courseDetails);
	 * 
	 * return response; }
	 */

	// 1
	/*
	 * @PayloadRoot(namespace = "http://_himanshu.gawari.com/courses", localPart =
	 * "GetCourseDetailsRequest")
	 * 
	 * @ResponsePayload public GetCourseDetailsResponse
	 * processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request)
	 * { GetCourseDetailsResponse response = new GetCourseDetailsResponse();
	 * 
	 * CourseDetails courseDetails = new CourseDetails();
	 * courseDetails.setId(request.getId());
	 * courseDetails.setName("Microservices Course");
	 * courseDetails.setDescription("That would be a wonderful course!");
	 * 
	 * response.setCourseDetails(courseDetails);
	 * 
	 * return response; }
	 */
}
