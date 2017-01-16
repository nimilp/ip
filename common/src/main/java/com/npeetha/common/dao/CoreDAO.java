package com.npeetha.common.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CoreDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession(){
		Session session = sessionFactory.getCurrentSession();
		if(session == null || !session.isConnected() || !session.isOpen()){
			session = sessionFactory.openSession();
		}
		return session;
	}
}
