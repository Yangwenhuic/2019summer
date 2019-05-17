package com.neu.myapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.myapp.pojo.Advisor;

public class AdvisorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.isAssignableFrom(Advisor.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name" ,"error.name.required", "name can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"staffId" ,"error.staffId.required", "staffId can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email" ,"error.email.required", "email can not be empty");

		if(errors.hasErrors())
			return;
		Advisor advisor = (Advisor) target;
		String pattern = "\\d{9}";
		
		Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(advisor.getStaffId());  
        boolean b = m.matches();
        if(!b){
            errors.rejectValue("staffId", "error.staffId.format"," invalid ID, ID must be a number and can only have 9 digit!");
            return;
        }
        
        String s = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        Pattern p1 = Pattern.compile(s);  
        Matcher m1 = p1.matcher(advisor.getEmail());  
        boolean b1 = m1.matches();
        if(!b1){
            errors.rejectValue("email", "error.email.format"," invalid Email");
            return;
        }
	}

}
