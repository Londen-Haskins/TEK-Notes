package com.LondenHaskins.Capstone.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="content_text")
	private String contentText;
	
	@ManyToOne
	@JoinColumn(name="author_id", nullable=false)
	private User author;
	
	@Column(name="time_posted")
	private Timestamp timePosted;
	
	@OneToMany(targetEntity=PostComment.class, mappedBy="refPost",cascade= {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)    
	private List<PostComment> comments = new ArrayList<>();
	
}
