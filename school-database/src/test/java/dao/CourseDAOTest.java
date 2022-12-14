package dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import school.dao.CourseDAO;
import school.entity.Course;


public class CourseDAOTest {
	
	private static CourseDAO courseDAO;
	
	@BeforeAll
	public static void setUp() {
		courseDAO = new CourseDAO();
	}
	
	@Test
	public void testGetCourseById() {
		// given
		Course expected = new Course();
		
		expected.setId(1);
		expected.setName("Accounting & Finance");
		expected.setDeptId(2);
		
		// when 
		Course actual = courseDAO.findById(1);
		
		// then
		Assertions.assertEquals(expected, actual);
		Assertions.assertEquals(expected.getName(), actual.getName());
		Assertions.assertEquals(expected.getDeptId(), actual.getDeptId());
	}

	@Test
	public void testGetCourseByName() {
		// given
		Course expected = new Course();
		
		expected.setId(1);
		expected.setName("Accounting & Finance");
		expected.setDeptId(2);
		
		// when 
		List<Course> actualList = courseDAO.findByCourseName(expected.getName());
		
		// then
		Assertions.assertTrue(actualList.size() > 0);
		
		Course actual = actualList.get(0);
		Assertions.assertEquals(expected, actual);
		Assertions.assertEquals(expected.getName(), actual.getName());
		Assertions.assertEquals(expected.getDeptId(), actual.getDeptId());
	}

}