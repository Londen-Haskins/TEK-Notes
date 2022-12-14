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
import com.LondenHaskins.Capstone.Entity.PostComment;
import com.LondenHaskins.Capstone.Entity.User;

@Repository
public interface PostCommentDAO extends JpaRepository<PostComment,Long> {

	@Query("SELECT u FROM User u WHERE u.id = :id")
	public User getAuthor(Integer id);
	
}
