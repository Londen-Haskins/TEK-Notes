package school.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import school.entity.Course;

public class CourseDAO {

	// this is the meachanics of getting a record from the database
	public Course findById(Integer id) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		String hql = "SELECT c FROM Course c where c.id = :pk";

		TypedQuery<Course> query = session.createQuery(hql, Course.class);
		query.setParameter("pk", id);

		Course result = query.getSingleResult();

		t.commit();
		factory.close();
		session.close();

		return result;
	}
	
	// HOMEWORK - make a new function that has a query that uses a like statement so I can 
	// find all courses that begin with a certain word.  so .. all courses that begin with A
	// or all courses that begin with History ... 
	public List<Course> findByCourseName(String name) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		String hql = "SELECT c FROM Course c where c.name = :name";

		TypedQuery<Course> query = session.createQuery(hql, Course.class);
		query.setParameter("name", name);

		List<Course> result = query.getResultList();

		t.commit();
		factory.close();
		session.close();

		return result;
	}
	
	
	public void insert(Course course ) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		session.save(course);

		t.commit();
		factory.close();
		session.close();
	}
	
	
	public void update(Course order ) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		session.merge(order);

		t.commit();
		factory.close();
		session.close();
	}
	
}