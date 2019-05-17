package com.neu.myapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.neu.myapp.pojo.Account;

public class AccountValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.isAssignableFrom(Account.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"accountName" ,"error.accountName.required", "account Name can not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password" ,"error.password.required", "password can not be empty");

	}

}
