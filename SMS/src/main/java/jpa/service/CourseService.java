package jpa.service;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class CourseService implements CourseDAO{

	// This method takes no parameter and returns every Course in the table.
	public List<Course> getAllCourses() {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		String hql = "FROM Course c";

		TypedQuery<Course> query = session.createQuery(hql, Course.class);

		List<Course> result = query.getResultList();
//		for (Course u : result) {
//	    	 System.out.println("Course ID: " +u.getCId() + "|" + " Name:" + u.getCName() +"|"+ "Instructor: " + u.getCInstructorName());
//	    }

		t.commit();
		factory.close();
		session.close();

		return result;
	}

}
