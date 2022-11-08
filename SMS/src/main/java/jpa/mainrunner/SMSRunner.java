package jpa.mainrunner;
import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StudentService stu = new StudentService();
		CourseService cou = new CourseService();
		char selection;
		boolean rerun = false;
		
		System.out.println("Are you a(n) /n 1. Student/n 2. Quit");
		System.out.println("Please enter, 1 or 2");
		selection =sc.next().charAt(0);
		
		// Main menu loop
		do {
			if(selection == '1') {
				
				System.out.print("Enter your email:  ");
				String inputEmail = sc.nextLine();
				System.out.print("Enter your password:  ");
				String inputPass = sc.nextLine();
				boolean validStudent = stu.validateStudent(inputEmail,inputPass);
				
				//Login successful
				if(validStudent) {
					// Get all courses assigned to student
					// List<Course> studentCourses = stu.getStudentCourses(inputEmail);
					// Give 'Register to Class' and 'Quit' option select
					boolean registerClass = false;
					if(registerClass) {
						// List all courses and ask for course selection
						// List<Course> listCourse = cou.getAllCourses();
						// Add student to course if not already registered
					}
				}
				
			}
			// Ask user if they want to keep running
		}while(rerun);
		
		
		List<Course> list = stu.getStudentCourses("htaffley6@columbia.edu");
		//boolean judge = stu.validateStudent("htaffley6@columbia.edu","xowtOQ");
	}

}
