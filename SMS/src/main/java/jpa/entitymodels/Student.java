package jpa.entitymodels;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="student")

public class Student {
	@Id
	@Column(name="email")
	private String sEmail;
	
	@Column(name="name")
	private String sName;
	
	@Column(name="password")
	private String sPass;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name="student_course",
			joinColumns = {@JoinColumn(name = "student_email", referencedColumnName = "email",nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id",nullable = false, updatable = false)}
			)
	private List<Course> sCourses = new ArrayList<Course>();
	
	public Student(String newEmail,String newName,String newPass) {
		this.sEmail = newEmail;
		this.sName = newName;
		this.sPass = newPass;
	}
	
	@Override
	public boolean equals(Object c) {
		
		if (c == this) {
            return true;
        }
		if (!(c instanceof Student)) {
            return false;
        }
		
		Student temp = (Student) c;
		if(!(this.sEmail == temp.sEmail)) {
			return false;
		}
		if(!(this.sName == temp.sName)) {
			return false;
		}
		if(!(this.sPass == temp.sPass)) {
			return false;
		}
		
		return true;
		
	}

}
