package com.assignment.survey.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.survey.bean.Answer;
import com.assignment.survey.bean.Option;
import com.assignment.survey.bean.Question;
import com.assignment.survey.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml", "classpath:META-INF/spring-mvc.xml"})
@Transactional
public class AnswerDaoTest {
	@Autowired
	private AnswerDao dao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private OptionDao optionDao;
	
	@Test
	public void  getAnswer() {
		Answer answer = dao. getAnswer(1L, 1L);
		System.out.println(answer);
	}
	
	@Test
	public void get() {
	    Answer answer = dao.get(1L);
	    System.out.println(answer);
	}
	
	@Test
	public void save() {
		User user = userDao.get(1L);
		Question question = questionDao.get(1L);
		Option option = optionDao.get(1L);
		if (null != user && null != question && null != option) {
			Answer answer = new Answer();
			answer.setQuestionId(question.getId());
			answer.setUserId(user.getId());
			answer.setOptionId(option.getId());;
		    Long id = dao.save(answer);
		    assert(id != null);
		}
	}
	
	@Test
	public void update() {
		Answer answer = dao.get(1L);
		if (null != answer) {
		    answer.setOptionId(2L);
		    dao.update(answer);
		    answer = dao.get(answer.getId());
		    assert(answer.getOptionId().equals(2L));
		}
	}
	
	@Test
	public void delete() {
		if (null != dao.get(1L)) 
			dao.delete(1L);
	}

}
