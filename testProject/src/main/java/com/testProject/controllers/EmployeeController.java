package com.testProject.controllers;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.testProject.models.Employee;

public class EmployeeController {

	// this is the mechanics of getting a record from the database
	public Employee findByEmployeeId(Integer id) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		String hql = "FROM Employee e where e.employeeNumber = :empId";

		TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
		query.setParameter("empId", id);

		Employee result = query.getSingleResult();

		t.commit();
		factory.close();
		session.close();

		return result;
	}

	// this is the meachanics of getting a record from the database
	public Employee findByCustomerNumber(Integer customerNumber) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		String hql = "select e from Employee e, Customer c where e.employeeNumber = c.salesRep.employeeNumber "
				+ "and c.id = :custNum";

		TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
		query.setParameter("custNum", customerNumber);

		Employee result = query.getSingleResult();

		t.commit();
		factory.close();
		session.close();

		return result;
	}

}