package com.neu.myapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.myapp.pojo.Course;
import com.neu.myapp.pojo.Student;



public class CourseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.isAssignableFrom(Course.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"capacity","error.capacity.required" , "capacity can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"courseInfo","error.courseInfo.required" , "courseInfo can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"courseName","error.courseName.required" , "courseName can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"crn" ,"error.crn.required", "crn can not be empty");


		if(errors.hasErrors())
			return;
		Course c = (Course) target;
		int capacity = c.getCapacity();
		if(capacity<0) {
			errors.rejectValue("capacity", "error.capacity.format", "the capacity of course can not be negative");
		}
		
		String pattern = "\\d{6}";
	       Pattern p = Pattern.compile(pattern);  
	        Matcher m = p.matcher(c.getCrn());  
	        boolean b = m.matches();
	        if(!b) {
	        	errors.rejectValue("crn", "error.crn.format", "course crn must be number and can only contain 6 digit");
	        }
	}
}
