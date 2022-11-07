package com.testProject.controllers;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.testProject.models.Customers;
import com.testProject.models.Orders;

public class CustomerController {

	// this is the meachanics of getting a record from the database
	public Customers findByCustomerId( Integer id ) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		String hql = "FROM Customer c where c.id = :custId";
	 	
		TypedQuery<Customers> query = session.createQuery(hql, Customers.class);
	 	query.setParameter("custId", id);
	 	
	 	Customers result = query.getSingleResult();

		t.commit();
		factory.close();
		session.close();
		
		return result;
	}
	
	
	
}