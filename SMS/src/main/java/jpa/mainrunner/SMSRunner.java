package jpa.mainrunner;
import java.util.List;

import jpa.entitymodels.Student;
import jpa.service.StudentService;

public class SMSRunner {

	public static void main(String[] args) {

		StudentService stu = new StudentService();
		//List<Student> list = stu.getAllStudents();
		boolean judge = stu.validateStudent("htaffley6@columbia.edu","xowtOQ");
	}

}
