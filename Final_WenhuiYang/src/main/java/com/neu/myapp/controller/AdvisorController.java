package com.neu.myapp.controller;

import java.util.List;

import javax.servlet.http.Cookie;
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

import com.neu.myapp.dao.AdvisorDao;
import com.neu.myapp.dao.StudentDao;
import com.neu.myapp.exception.StudentException;
import com.neu.myapp.pojo.Advisor;
import com.neu.myapp.pojo.Student;
import com.neu.myapp.validator.AdvisorValidator;

@Controller
@RequestMapping("/advisor")
public class AdvisorController {

	@Autowired
	StudentDao studentDao;
	
	@Autowired 
	AdvisorDao advisorDao;
	
	@Autowired
	AdvisorValidator advisorValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(advisorValidator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewStudents(Model model,HttpServletRequest request) throws StudentException {
		HttpSession session = request.getSession();
		String s = (String) session.getAttribute("admin");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			return "home";
		}
		try {
			List<Student> list = studentDao.list();
			model.addAttribute("studentList", list);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return "studentList";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String assignStudent(Model model, HttpServletRequest request) {
		String[] students =(String[]) request.getParameterValues("students");
		HttpSession session = request.getSession();
		session.setAttribute("studentsTobeAssign", students);
		model.addAttribute("newAdvisor", new Advisor());
		
		return "AssignAdvisor";
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public String assignAdvisor(@Validated@ModelAttribute("newAdvisor") Advisor advisor, BindingResult bindingResult, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String[] students = (String[])session.getAttribute("studentsTobeAssign");
		if (bindingResult.hasErrors()) {			 
			return "AssignAdvisor";
		}
		
		try {
			advisorDao.createAdvisor(advisor);
			for (int i =0; i<students.length; i++) {
				Student student = studentDao.get(students[i]);
				student.setAdvisor(advisor);
				studentDao.update(student);
			}
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "something wrong happend please login first");
			return "home";
		}
		return "AdminSuccessView";
	}
	
}
