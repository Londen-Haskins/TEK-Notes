package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import school.entity.Course;

public class CourseTest {
	
	private static Course course;
	
	@BeforeAll
	public static void setUp() {
		course = new Course();
	}

	
	@Test
	public void testGetId() {
		Course expected = new Course();
		
		expected.setId(1);
		
		// when 
		Course actual = course;
		actual.setId(1);
		Assertions.assertEquals(expected.getId(), actual.getId());
		
	}
	
	@Test
	public void testGetName() {
		Course expected = new Course();
		
		expected.setName("Gym");
		
		// when 
		Course actual = course;
		actual.setName("Gym");
		Assertions.assertEquals(expected.getName(), actual.getName());
		
	}
	
	@Test
	public void testGetDeptId() {
		Course expected = new Course();
		
		expected.setDeptId(4657);
		
		// when 
		Course actual = course;
		actual.setDeptId(4657);
		Assertions.assertEquals(expected.getDeptId(), actual.getDeptId());
		
	}
	
}