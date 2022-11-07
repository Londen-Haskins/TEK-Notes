package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import school.entity.Department;

public class DepartmentTest {

private static Department department;
	
	@BeforeAll
	public static void setUp() {
		department = new Department();
	}

	
	@Test
	public void testGetId() {
		Department expected = new Department();
		
		expected.setId(1);
		
		// when 
		Department actual = department;
		actual.setId(1);
		Assertions.assertEquals(expected.getId(), actual.getId());
		
	}
	
	@Test
	public void testGetName() {
		Department expected = new Department();
		
		expected.setName("Humanities");
		
		// when 
		Department actual = department;
		actual.setName("Humanities");
		Assertions.assertEquals(expected.getName(), actual.getName());
		
	}
	
}
