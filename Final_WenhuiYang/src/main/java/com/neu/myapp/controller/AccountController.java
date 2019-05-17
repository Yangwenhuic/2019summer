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
import com.neu.myapp.dao.StudentDao;
import com.neu.myapp.exception.AccountException;
import com.neu.myapp.exception.StudentException;
import com.neu.myapp.pojo.Account;
import com.neu.myapp.pojo.Student;
import com.neu.myapp.validator.AccountValidator;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountDao accountDao;
	@Autowired
	StudentDao studentDao;
	@Autowired 
	AccountValidator accountvalidator;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(accountvalidator);
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView RegisterAccount(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView ();
		Student student =(Student) request.getSession().getAttribute("newStudent");
		if(student ==null) {
			mv.addObject("error", "please start from the beginning");
			mv.setViewName("home");
			return mv;
		}
		mv.addObject("NewAccount", new Account());
		mv.setViewName("CreateAccount");
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public ModelAndView RegisterAccount(@Validated @ModelAttribute("NewAccount") Account newAccount,BindingResult bindingResult,Model model,HttpServletRequest request) 
	throws StudentException , AccountException{
		Student student =(Student) request.getSession().getAttribute("newStudent");
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			//mv.addObject("NewAccount", new Account());
			mv.setViewName("CreateAccount");
			return mv;  //the are validation errors, go to the form view
		}
		if(newAccount.getAccountName().equals("admin")) {
			mv.addObject("NewAccount", new Account());
			mv.setViewName("CreateAccount");
			model.addAttribute("error", "this account name is already exist");
			return mv;
			
		}
		
		try {
			if(accountDao.isExist(newAccount)) {
				mv.addObject("NewAccount", new Account());
				mv.setViewName("CreateAccount");
				model.addAttribute("error", "this account name is already exist");
				return mv;
			}
		}catch (Exception e) {
			e.printStackTrace();
			mv.addObject("NewAccount", new Account());
			mv.setViewName("CreateAccount");
			model.addAttribute("error", "please try again");
			return mv;
		}
		try {
			student.setStudentAccount(newAccount);
			studentDao.createStudent(student);
		}catch (Exception e ){
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("student", student);
		mv.setViewName("RegisterSuccess");
		return mv;
	}
}
