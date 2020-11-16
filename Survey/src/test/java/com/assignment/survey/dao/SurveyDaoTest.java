package com.assignment.survey.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.Survey;
import com.assignment.survey.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml", "classpath:META-INF/spring-mvc.xml"})
@Transactional
public class SurveyDaoTest {
	@Autowired
	private SurveyDao dao;
	@Autowired
	private UserDao userDao;
	
	private User user;
	
	@Before
	public void setup() {
		user = userDao.getByNameEmail("test", "test@test.com");
	}
	
	@Test
	public void getByCreator() {
		if (null != user) {
			List<Survey> list = dao.getByCreator(user.getId());
			assert(list.size() > 0);
		}
	}

	@Test
	public void getAnswered() {
		if (null != user) {
			List<Survey> list = dao.getAnswered(user.getId());
			assert(list.size() > 0);
		}
	}

	@Test
	public void isAnswered() {
		boolean isAnswered = dao.isAnswered(1L);
		System.out.println(isAnswered);
	}
	
	@Test
	public void get() {
	    Survey s = dao.get(1L);
	    System.out.println(s);
	}
	
	@Test
	public void save() {
		if (null != user) {
			Survey s = new Survey();
			s.setTitle("test survey");
			s.setCreator(user.getId());
		    Long id = dao.save(s);
		    assert(id != null);
		}
	}
	
	@Test
	public void update() {
	    Survey s = dao.get(1L);
	    if (null != s) {
		    s.setTitle("test survey v2");
		    dao.update(s);
		    s = dao.get(s.getId());
		    assertEquals("test survey v2", s.getTitle());
	    }
	}
	
	@Test
	public void delete() {
		if (null != dao.get(1L))
			dao.delete(1L);
	}

}
