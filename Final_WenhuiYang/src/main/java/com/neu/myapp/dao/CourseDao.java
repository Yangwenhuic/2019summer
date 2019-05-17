package com.neu.myapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.neu.myapp.exception.CourseException;
import com.neu.myapp.pojo.Course;



public class CourseDao extends DAO{

	public CourseDao() {
		
	}
	public Course createCouse(Course course) throws CourseException{
		try {
			begin();
			getSession().save(course);
			commit();
			return course;
		} catch (HibernateException e) {
			rollback();
			throw new CourseException("Exception while creating course: " + e.getMessage());
		}
	}
	
	public void update(Course course) throws CourseException {
		try {
			begin();
			getSession().update(course);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CourseException("Could not save the course", e);
		}
	}
	
    public List<Course> list() throws CourseException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Course");
            List<Course> courses =(List<Course>) q.list();
            commit();
            return courses;
        } catch (HibernateException e) {
            rollback();
            throw new CourseException("Could not search course", e);
        }
    	
    }
    
	public Course get(String crn) throws CourseException {
		try {
			begin();
			Query q = getSession().createQuery("from Course where crn = :crn");
			q.setString("crn", crn);
			Course course = (Course) q.uniqueResult();
			commit();
			return course;
		} catch (HibernateException e) {
			rollback();
			throw new CourseException("Could not get course " + crn, e);
		}
	}
	
	public List<Course> searchByName(String name) throws CourseException {
		try {
			begin();
			Query q = getSession().createQuery("from Course where courseName = :courseName");
			q.setString("courseName", name);
			List<Course> courses = (List<Course>) q.list();
			commit();
			return courses;
		} catch (HibernateException e) {
			rollback();
			throw new CourseException("Could not get course " + name, e);
		}
	}
	
	public List<Course> searchByInfo(String info) throws CourseException {
		try {
			begin();
			Criteria c = getSession().createCriteria(Course.class);
			c.add(Restrictions.ilike("courseInfo", info, MatchMode.ANYWHERE));
			List<Course> courses = (List<Course>) c.list();
			commit();
			return courses;
		} catch (HibernateException e) {
			rollback();
			throw new CourseException("Could not get course " + info, e);
		}
	}
	
	public void delete(String crn) throws CourseException {
		try {
			begin();
			 Session session = getSession();
				Query q = session.createQuery("from Course where crn = :crn");
				q.setString("crn", crn);
				Course course = (Course) q.uniqueResult();
				session.delete(course);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CourseException("Could not delete course " + crn, e);
		}
	}
	
	public void closeCourseDao()throws CourseException{
		try {
			close();
		}catch (HibernateException e) {
			rollback();
			throw new CourseException("Could not close course dao " );
		}
	}
}
