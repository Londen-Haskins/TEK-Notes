package school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@AllArgsConstructor
@Entity
@Table(name="course")
public class Course {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	// HOMEWORK - research insert and updateabele in the @column
	// this is saying to hibernate that this is a read only field.
	// it will not update this field to the database nor insert it
	// this is for conviencance
	@Column(name="deptId", insertable=false, updatable = false)
	private Integer deptId;
	
	// the join column is the actualy database column name for the foreign key in the course table
	// this is one of the few rare spots in which a database name is actually used
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "deptId", nullable = false)
    private Department department;
}