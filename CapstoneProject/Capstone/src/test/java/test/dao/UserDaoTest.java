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

import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserDaoTest {
	
	@Autowired
	private static UserDAO userDao;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void findByIdTest() {

		User tester = userDao.findById(9);

		Assertions.assertThat(tester.getId()).isEqualTo(9);
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	public void getAllUsersTest() {
		List<User> users = userDao.getAllUsers();
		Assertions.assertThat(users.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	@Rollback(value = false)
	public void findByEmailTest() {

		User tester = userDao.findByEmail("jwall@gmail.com");

		Assertions.assertThat(tester.getEmail()).isEqualTo("jwall@gmail.com");
	}
	
}
