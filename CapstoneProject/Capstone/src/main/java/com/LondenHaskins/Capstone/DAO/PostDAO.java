package com.LondenHaskins.Capstone.DAO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.LondenHaskins.Capstone.Entity.Post;
import com.LondenHaskins.Capstone.Entity.User;

@Repository
public interface PostDAO extends JpaRepository<Post,Long> {

	public Post findById(Integer id);
	
	@Query("SELECT p FROM Post p")
	public List<Post> getAllPosts();
	
	@Query("SELECT p FROM Post p WHERE author =:user ")
	public List<Post> getAllPostsFrom(User user);
	
	@Query("SELECT COUNT(p) FROM PostComment p WHERE refPost = :post")
	public Integer getNumComments(Post post);
	
}
