package jpa.entitymodels;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name="course")

public class Course {
	
	@Id
	@Column(name="id")
	private int cId;
	
	@Column(name="name")
	private String cName;
	
	@Column(name="instructor")
	private String cInstructorName;
	
	@ManyToMany(mappedBy = "sCourses", fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<Student>();
	
	public Course(int newId,String newName,String newInstructName) {
		this.cId = newId;
		this.cName = newName;
		this.cInstructorName = newInstructName;
	}
	
	@Override
	public boolean equals(Object c) {
		
		if (c == this) {
            return true;
        }
		if (!(c instanceof Course)) {
            return false;
        }
		
		Course temp = (Course) c;
		if(!(this.cId == temp.cId)) {
			return false;
		}
		if(!(this.cName == temp.cName)) {
			return false;
		}
		if(!(this.cInstructorName == temp.cInstructorName)) {
			return false;
		}
		
		return true;
		
	}

}
