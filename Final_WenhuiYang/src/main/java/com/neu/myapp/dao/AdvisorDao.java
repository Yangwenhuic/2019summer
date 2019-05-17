package com.neu.myapp.dao;

import org.hibernate.HibernateException;

import com.neu.myapp.exception.AdvisorException;
import com.neu.myapp.pojo.Advisor;



public class AdvisorDao extends DAO {
	public Advisor createAdvisor(Advisor advisor) throws AdvisorException{
		try {
			begin();
			getSession().save(advisor);
			commit();
			return advisor;
		} catch (HibernateException e) {
			rollback();
			throw new AdvisorException("Exception while creating advisor: " + e.getMessage());
		}
	}
}
