package jpa.dao;
import java.util.List;

import jpa.entitymodels.Student;
import jpa.entitymodels.Course;


public interface StudentDAO {
	
	public List<Student> getAllStudents();
	
	public Student getStudentByEmail(String inputEmail);
	
	public boolean validateStudent(String inputEmail, String inputPass);
	
	public void registerStudentToCourse(String inputEmail, int inputId);
	
	public List<Course> getStudentCourses(String inputEmail);
	

}
