package jpa.mainrunner;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
		sc.nextLine();
		
		// Main menu loop
		do {
			switch(selection){
				case '1':
				
					System.out.println("Enter your email:  ");
					String inputEmail = sc.nextLine();
					System.out.print("Enter your password:  ");
					String inputPass = sc.nextLine();
					boolean validStudent = stu.validateStudent(inputEmail,inputPass);
					
					//Login successful
					if(validStudent) {
						// Get all courses assigned to student
						List<Course> studentCourses = stu.getStudentCourses(inputEmail);
						System.out.println("USER COURSES:\n");
						System.out.println(studentCourses.toString());
						// Give 'Register to Class' and 'Quit' option select
						System.out.println(" 1. Register to Class\n 2.Quit");
						char register = sc.next().charAt(0);
						sc.nextLine();
						
						if(register == '1') {
							// List all courses and ask for course selection
							List<Course> listCourse = cou.getAllCourses();
							System.out.println("COURSE LIST\n");
							System.out.println(listCourse.toString());
							System.out.println("Enter the class ID you would like to register for.");
							String courseChoice = sc.nextLine();
							
							// Add student to course if not already registered
							try {
								int choiceId = Integer.parseInt(courseChoice);
								stu.registerStudentToCourse(inputEmail,choiceId);
							}catch(Exception e) {
								System.out.println("Invalid entry for registration");
							}
														
						}else if(register == '2'){
							//Quit program
							rerun = false;
						
						}
					}
					break;
				case '2':
					//User choose to quit program
					//quit()
					break;
				default:
					System.out.println("Invalid menu entry");
					break;
				
			}
			
			// Ask user if they want to keep running
			System.out.println("Do you want to run program again?\n Press 'y' for yes and 'n' for no");
			char rewind = sc.next().charAt(0);
			sc.nextLine();
			switch(rewind){
				case 'y':
					rerun = true;
					break;
				case 'n':
					rerun = false;
					break;
				default:
					System.out.println("Invalid entry");
					break;
			}
			
		}while(rerun);
		System.out.println("Closing program");
		
		
		//TEST ACCOUNT("htaffley6@columbia.edu","xowtOQ");
	}

}
