package com.neu.myapp.controller;

import java.util.ArrayList;
import java.util.Set;

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
import com.neu.myapp.dao.CourseDao;
import com.neu.myapp.dao.StudentDao;
import com.neu.myapp.exception.AccountException;
import com.neu.myapp.exception.CourseException;
import com.neu.myapp.exception.StudentException;
import com.neu.myapp.pojo.Account;
import com.neu.myapp.pojo.Advisor;
import com.neu.myapp.pojo.Course;
import com.neu.myapp.pojo.Student;
import com.neu.myapp.validator.StudentValidator;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired 
	StudentValidator studentvalidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(studentvalidator);
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView RegisterStudent() {
		ModelAndView mv = new ModelAndView ();
		mv.addObject("NewStudent", new Student());
		mv.setViewName("register");
		return mv;

	}
	@RequestMapping(method= RequestMethod.POST)
	public ModelAndView RegisterStudentPost(@Validated @ModelAttribute("NewStudent") Student newStudent,BindingResult bindingResult,Model model, HttpServletRequest request){
		ModelAndView mv = new ModelAndView ();
		HttpSession session = request.getSession();
		if(bindingResult.hasErrors()) {
			//mv.addObject("NewStudent", new Student());
			mv.setViewName("register");
			return mv;
		}
		Student s = null;
		try {
			s= studentDao.get(newStudent.getStudentId());
		}catch(Exception e ) {
			e.printStackTrace();
		}
		if(s!=null) {
			mv.addObject("NewStudent", new Student());
			mv.setViewName("register");
			model.addAttribute("error", "this id is already exist");
			return mv;
		}
		session.setAttribute("newStudent", newStudent);
		
		mv.setViewName("createStudentSuccess");
		return mv;

	}
	
	
	@RequestMapping(value = "/info",method= RequestMethod.GET)
	public String ManageStudent(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("student");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");

			return "home";
		}
		
		return "StudentManage";

	}
	@RequestMapping(value = "/basicInfo",method= RequestMethod.GET)
	public String ManageStudentInfoget(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("student");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");

			return "home";
		}
		model.addAttribute("info", s.getStudentInfo());
		
		return "StudentManageBasicInfo";

	}
	@RequestMapping(value = "/myCourse",method= RequestMethod.GET)
	public String ManageStudentCourseget(Model model,HttpServletRequest request) {
		Set courselist = null;
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("student");
		try {
			studentDao.closeStudentDao();
		s= studentDao.get(s.getStudentId());
		session.setAttribute("student", s);
		//courseDao.list();
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "something wrong happend please login first");

			return "home";
		}
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");

			return "home";
		}
		
		courselist = s.getCourseList();
		model.addAttribute("courseList", courselist);
		return "StudentManageCourse";

	}
	
	@RequestMapping(value = "/basicInfo",method= RequestMethod.POST)
	public String ManageStudentInfo(Model model,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("student");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");

			return "home";
		}
		String info = (String)request.getParameter("info");
		Student student = null;
		try {
			student = studentDao.get(s.getStudentId());
			student.setStudentInfo(info);
			studentDao.update(student);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		session.setAttribute("student", student);
		model.addAttribute("message", "Information edit");
		return "StudentSuccessView";

	}
	
	@RequestMapping(value = "/myCourse",method= RequestMethod.POST)
	public String ManageStudentCourse(Model model,HttpServletRequest request) throws CourseException {
		
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("student");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			return "home";
		}
		String [] courses =(String []) request.getParameterValues("courses");
		for(int i =0; i<courses.length; i++) {
			try {
				courseDao.closeCourseDao();
				Course course = courseDao.get(courses[i]);
				course.getStudents().remove(studentDao.get(s.getStudentId()));
				courseDao.update(course);
				int remain = course.getCapacity();
				System.out.println(remain+ "remain");
				course.setCapacity(remain+1);
				course.setRegisterable(true);
				courseDao.update(course);
				s = studentDao.get(s.getStudentId());
			}catch(Exception e) {
				e.printStackTrace();
				return "StudentErrorView";
			}
		}
		session.setAttribute("student", s);
		model.addAttribute("message", "courseRemove");
		return "StudentSuccessView";

	}
	
	@RequestMapping(value = "/myAdvisor",method= RequestMethod.GET)
	public String ViewAdvisor(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		Student s = (Student)session.getAttribute("student");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			return "home";
		}
		
		try {
			studentDao.closeStudentDao();
		 s = studentDao.get(s.getStudentId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		Advisor ad = s.getAdvisor();
		if(ad == null) {
			model.addAttribute("error", "Your advisor has not assigned yet, you may check that later");
			return "StudentAdvisor";
		}
		session.setAttribute("myAdvisor", ad);
		return "StudentAdvisor";

	}
	
}
