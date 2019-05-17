package com.neu.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.myapp.exception.AccountException;
import com.neu.myapp.pojo.Account;
import com.neu.myapp.pojo.Student;


public class AccountDao extends DAO{

	public AccountDao() {
	}

	public Account login(Account account) throws AccountException {
		try {
			begin();
			Query q = getSession().createQuery("from Account where accountName = :accountName");
			q.setString("accountName", account.getAccountName());
			Account acc = (Account) q.uniqueResult();
			if(acc!=null&&!acc.getPassword().equals(account.getPassword())) {
				acc= null;
			}
			commit();
			return acc;
		} catch (HibernateException e) {
			rollback();
			throw new AccountException("Could not get account " + account.getAccountName(), e);
		}
	}

	public Account register(Account account) throws AccountException {
		try {
			begin();
			getSession().save(account);
			commit();
			return account;
		} catch (HibernateException e) {
			rollback();
			throw new AccountException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public boolean isExist(Account account) throws AccountException{
		try {
			begin();
			Query q=getSession().createQuery("from Account where accountName = :accountName");
			q.setString("accountName", account.getAccountName());
			List list = q.list();
			if(list.size() ==0)
				return false;
			else
				return true;
		}catch (Exception e ) {
			rollback();
			throw new AccountException("Exception while searching account: " + e.getMessage());			
		}
	}

}
