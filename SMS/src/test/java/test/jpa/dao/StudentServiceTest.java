package test.jpa.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jpa.entitymodels.Student;
import jpa.service.StudentService;

public class StudentServiceTest {
	private static StudentService studentService;
	@BeforeAll
	public static void setUp() {
		studentService = new StudentService();
	}
	@Test
	public void testGetStudentById() {
		Student expected = new Student();
		expected.setSEmail("htaffley6@columbia.edu");
		expected.setSName("Holmes Taffley");
		Student actual = studentService.getStudentByEmail("htaffley6@columbia.edu");
		assertEquals(expected.getSEmail(), actual.getSEmail());
	}
}
