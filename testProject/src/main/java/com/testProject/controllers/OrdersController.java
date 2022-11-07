package com.testProject.controllers;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.testProject.models.Orders;

public class OrdersController {
	
	// this is the mechanics of getting a record from the database
	public Orders findByOrderNumber( Integer orderNumber ) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		String hql = "FROM Order o where o.orderNumber = :orderNo";
	 	
		TypedQuery<Orders> query = session.createQuery(hql, Orders.class);
	 	query.setParameter("orderNo", orderNumber);
	 	
	 	Orders result = query.getSingleResult();

		t.commit();
		factory.close();
		session.close();
		
		return result;
	}
	
	public List<Orders> findByStatus( String status ) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		String hql = "FROM Order o where o.status = :stat";
	 	
		TypedQuery<Orders> query = session.createQuery(hql, Orders.class);
	 	query.setParameter("stat", status);
	 	
	 	// by using getResultList .. it will return a list of Order objects that represent the 
	 	// rows in the database.
	 	List<Orders> result = query.getResultList();

		t.commit();
		factory.close();
		session.close();
		
		return result;
	}
	
	public void insert(Orders order ) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		session.save(order);

		t.commit();
		factory.close();
		session.close();
	}
	
	
	public void update(Orders order ) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		session.merge(order);

		t.commit();
		factory.close();
		session.close();
	}
	
}
