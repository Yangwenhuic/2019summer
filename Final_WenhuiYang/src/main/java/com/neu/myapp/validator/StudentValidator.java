package com.neu.myapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.myapp.pojo.Student;

public class StudentValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Student.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentId","error.studentId.required", "student id can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentInfo","error.studentInfo.required", "student info can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentName","error.studentName.required", "student Name can not be empty");

		if(errors.hasErrors()) {
			return;
		}
		String pattern = "\\d{9}";
		Student s = (Student) target;
	       Pattern p = Pattern.compile(pattern);  
	        Matcher m = p.matcher(s.getStudentId());  
	        boolean b = m.matches();
	        if(!b){
	            errors.rejectValue("studentId", "error.studentId.format","id must be a number and can only have 9 digit!");
	        }
	}
	

}
