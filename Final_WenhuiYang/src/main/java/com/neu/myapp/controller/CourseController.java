package com.neu.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.myapp.dao.CourseDao;
import com.neu.myapp.dao.StudentDao;
import com.neu.myapp.pojo.Course;
import com.neu.myapp.pojo.Student;
import com.neu.myapp.validator.CourseValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.neu.myapp.exception.CourseException;
import com.neu.myapp.exception.StudentException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
@RequestMapping("/course/*")
public class CourseController {

	@Autowired
	CourseDao courseDao;

	@Autowired
	StudentDao studentDao;
	
	@Autowired 
	CourseValidator coursevalidator;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(coursevalidator);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createCourse(HttpServletRequest request,Model model) throws CourseException {
		
		HttpSession session = request.getSession();
		String s = (String) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			mv.setViewName("home"); 
			return mv;
		}
		
		mv.addObject("course", new Course());
		mv.setViewName("AddCourse");

		return mv;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createCoursePost(@Validated@ModelAttribute("course") Course course, BindingResult bindingResult, Model model,HttpServletRequest request)
			throws CourseException, StudentException {
		HttpSession session = request.getSession();
		String s = (String) session.getAttribute("admin");
		System.out.println(s);
		ModelAndView mv = new ModelAndView();
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			mv.setViewName("home"); 
			return mv;
		}
		
		if (bindingResult.hasErrors()) {
			//mv.addObject("course", new Course());
			mv.setViewName("AddCourse"); 
			return mv;
		}
		Course c = null;
		try {
			c= courseDao.get(course.getCrn());
		}catch(Exception e ) {
			e.printStackTrace();
		}
		if(c!=null) {
			mv.addObject("course", new Course());
			mv.setViewName("AddCourse");
			model.addAttribute("error", "this crn is already exist, one crn can only hold one course");
			return mv;
		}
		try {
			if(course.getCapacity()!=0) {
				course.setRegisterable(true);
			}
			courseDao.createCouse(course);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		mv.addObject("newCourse", course);
		mv.setViewName("AdminSuccessView");
		model.addAttribute("message", "course add ");
		
		return mv;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteCourse(Model model, HttpServletRequest request) throws CourseException, StudentException {

		String[] courselist = request.getParameterValues("courses");
		HttpSession session = request.getSession();
		String s = (String) session.getAttribute("admin");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			return "home";
		}
		if(courselist==null) {
			
		}

		try {
			for (int i = 0; i < courselist.length; i++) {
				String crn = courselist[i];
				Course course = courseDao.get(crn);
				course.getStudents().clear();
				courseDao.update(course);
				courseDao.delete(crn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			courseDao.closeCourseDao();
		}
		
		model.addAttribute("message", "course delete ");
		return "AdminSuccessView";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView SearchCourses(Model model, HttpServletRequest request)
			throws CourseException, StudentException {
		List<Course> courselist = null;
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		System.out.println(admin);
		ModelAndView mv = new ModelAndView();
		if (admin != null) {
			try {
				courselist = courseDao.list();

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				courseDao.closeCourseDao();
			}

			model.addAttribute("courseList", courselist);
			mv.setViewName("SearchForCourse");
		} else {
			model.addAttribute("error", "please log in first");
			mv.setViewName("home");
			return mv;
		}
		for(int i = 0; i<courselist.size(); i++) {
			System.out.println(courselist.get(i).getCapacity());
		}
		
		return mv;
	}

	@RequestMapping(value = "/Admin", method = RequestMethod.GET)
	public String Back() {

		return "Admin";
	}

	@RequestMapping(value = "/Student", method = RequestMethod.GET)
	public String BackStu() {

		return "Student";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet() {

		return "StudentSearchCourse";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerCourses(Model model, HttpServletRequest request) throws CourseException, StudentException {
		String[] courselist = request.getParameterValues("courses");
		HttpSession session = request.getSession();
		Student s = (Student) session.getAttribute("student");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			return "home";
		}

		try {
			for (int i = 0; i < courselist.length; i++) {

				String crn = courselist[i];
				Course course = courseDao.get(crn);
			
				int remain = course.getCapacity();
				if(remain ==1) {
					course.setRegisterable(false);
				}
				course.setCapacity(remain-1);
				//courseDao.update(course);
				s.addCourse(course);
				courseDao.update(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			courseDao.closeCourseDao();
		}
		session.setAttribute("student", s);
		return "StudentSuccessView";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchCourses(Model model, HttpServletRequest request)
			throws CourseException, StudentException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Student s = (Student) session.getAttribute("student");
		if (s == null) {
			model.addAttribute("error", "something wrong happend please login first");
			mv.setViewName("home");
			return mv;
		}
		List<Course> courselist = null;
		String searchBy = (String) request.getParameter("searchBy");
		String keyWord = (String) request.getParameter("keyWord");
		if(keyWord ==null) {
			courselist = new ArrayList();
		}
		try {
			courseDao.closeCourseDao();
			s = studentDao.get(s.getStudentId());
			if (searchBy.equals("crn")) {

				Course course = courseDao.get(keyWord);
				courselist = new ArrayList();
				if (course != null) {
					courselist.add(course);
				}

			}
			if (searchBy.equals("courseName")) {
				courselist = courseDao.searchByName(keyWord);
			}
			if (searchBy.equals("courseInfo")) {
				courselist = courseDao.searchByInfo(keyWord);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i =0; i<courselist.size();i++) {
			System.out.println(courselist.get(i).getCapacity());
			for(Course c: s.getCourseList()) {
				System.out.println(c.getCourseName());
				if(c.getCrn().equals(courselist.get(i).getCrn())) {
					courselist.get(i).setRegisterable(false);
				}
			}
		}
		
		model.addAttribute("courseList", courselist);
		mv.setViewName("RegisterCourse");
		courseDao.closeCourseDao();
		return mv;
	}
}
