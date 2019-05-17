package com.neu.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.myapp.exception.CourseException;
import com.neu.myapp.exception.StudentException;
import com.neu.myapp.pojo.Course;
import com.neu.myapp.pojo.Student;

public class StudentDao extends DAO {
	public StudentDao() {
		
	}
	public Student createStudent(Student student) throws StudentException{
		try {
			begin();
			getSession().save(student);
			commit();
			return student;
		} catch (HibernateException e) {
			rollback();
			throw new StudentException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void update(Student student) throws StudentException {
		try {
			begin();
			getSession().update(student);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new StudentException("Could not update the student", e);
		}
	}
	
	public void updateCourse(Student student) throws StudentException {
		try {
			begin();
			getSession().merge(student);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new StudentException("Could not update the student", e);
		}
	}
	
	public Student get(String ID) throws StudentException {
		try {
			begin();
			Query q = getSession().createQuery("from Student where studentId = :studentId");
			q.setString("studentId", ID);
			Student student = (Student) q.uniqueResult();
			commit();
			return student;
		} catch (HibernateException e) {
			rollback();
			throw new StudentException("Could not get student " + ID, e);
		}
	}
	
	public List<Student> list() throws StudentException{
		List<Student> list = null;
		try {
			begin();
			Query q = getSession().createQuery("from Student");
			 list = q.list();
			commit(); 
		}catch(HibernateException e ) {
			rollback();
			throw new StudentException("could not get Student list" );
		}
		if(list !=null) {
			List<Student> slist = new ArrayList<Student>();
			for(int i =0; i<list.size(); i++) {
				if(list.get(i).getAdvisor() ==null) {
					slist.add(list.get(i));
				}
			}
			return slist;
		}
		return new ArrayList<Student>();
		
	}
	
	public void closeStudentDao()throws StudentException{
		try {
			close();
		}catch (HibernateException e) {
			rollback();
			throw new StudentException("Could not close student dao " );
		}
	}

}
