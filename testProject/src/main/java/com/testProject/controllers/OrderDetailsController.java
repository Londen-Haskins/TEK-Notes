package com.testProject.controllers;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.testProject.models.OrderDetails;

public class OrderDetailsController {
	
	public void findOrderDetailsSelect()
    {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		/* ------------  Example of HQL to get all the records------- */
		String hql = "SELECT u FROM orderdetails u"; 
	    Query query = session.createQuery(hql);
	    List<OrderDetails> list  =  query.getResultList();
	    
	    for (OrderDetails u : list) {
			System.out.println("Product Code:" + u.getProductCode() +"|"
					+ "Quanity Ordered: " + u.getQuanityOrdered() +"|"+ "Price Each:" + u.getPriceEach() +"|"+ "Order Line Number:"+ u.getOrderLineNumber());
		}
	}
	
	public void getRecordbyId() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();		   
		String hql = "SELECT U FROM orderdetails U WHERE U.id < 5 ORDER BY U.id DESC"; 
		Query query = session.createQuery(hql);
		List<OrderDetails> list  =  query.getResultList();
		for (OrderDetails u : list) {
			System.out.println("Product Code:" + u.getProductCode() +"|"
					+ "Quanity Ordered: " + u.getQuanityOrdered() +"|"+ "Price Each:" + u.getPriceEach() +"|"+ "Order Line Number:"+ u.getOrderLineNumber());
		}
	}

}
