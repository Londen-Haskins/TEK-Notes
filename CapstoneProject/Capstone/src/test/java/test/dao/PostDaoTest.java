package test.dao;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.LondenHaskins.Capstone.DAO.PostDAO;
import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.Post;
import com.LondenHaskins.Capstone.Entity.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PostDaoTest {
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void getAllPostsTest() {
		List<Post> posts = postDao.getAllPosts();
		Assertions.assertThat(posts.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	public void getAllPostsFromTest() {
		User test = userDao.findById(9);
		List<Post> posts = postDao.getAllPostsFrom(test);
		Assertions.assertThat(posts.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	@Rollback(value = false)
	public void getNumCommentsTest() {
		Post test = postDao.findById(9);
		Integer testNum = postDao.getNumComments(test);
		Assertions.assertThat(testNum).isEqualTo(test.getComments().size());
	}

}
