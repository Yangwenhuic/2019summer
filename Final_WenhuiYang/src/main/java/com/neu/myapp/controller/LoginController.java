package com.neu.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.myapp.dao.AccountDao;
import com.neu.myapp.exception.AccountException;
import com.neu.myapp.pojo.Account;
import com.neu.myapp.pojo.Course;
import com.neu.myapp.validator.AccountValidator;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired 
	AccountDao accountDao;
	
	@Autowired 
	AccountValidator accountvalidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(accountvalidator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView Login(){
ModelAndView mv = new ModelAndView();
		mv.addObject("account",new Account());
		mv.setViewName("Login");
return mv;
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String Authantication(@Validated @ModelAttribute("account") Account account,BindingResult bindingResult,Model model,HttpServletRequest request)
	throws AccountException{

		if (bindingResult.hasErrors()) {
			//model.addAttribute("account", new Account());
			return "Login";  //the are validation errors, go to the form view
		}
		HttpSession session = request.getSession();
		if(isAdmin(account)) {
			session.setAttribute("admin", "valid");
		return "Admin";
		}else {

			Account login = accountDao.login(account);

			if(login!=null) {
				session.setAttribute("student", login.getStudent());
				return "Student";
			
			}else {
				model.addAttribute("error", "User doesn't exist or the password is wrong");
				return "home";
			}
		}

	}
	
	boolean isAdmin(Account account) {
		if(account.getAccountName().equals("admin")&&account.getPassword().equals("admin")) {
			return true;
		}
		return false;
	}
}
