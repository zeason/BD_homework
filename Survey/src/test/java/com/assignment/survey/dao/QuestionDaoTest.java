package com.assignment.survey.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.Question;
import com.assignment.survey.bean.Survey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml", "classpath:META-INF/spring-mvc.xml"})
@Transactional
public class QuestionDaoTest {
	@Autowired
	private QuestionDao dao;
	@Autowired
	private SurveyDao surveyDao;
	
	@Test
	public void getBySurveyId() {
		List<Question> list = dao.getBySurveyId(1L);
		System.out.println(list);
	}
	
	@Test
	public void get() {
	    Question q = dao.get(1L);
	    System.out.println(q);
	}
	
	@Test
	public void save() {
		Survey survey = surveyDao.get(1L);
		if (null != survey) {
			Question q = new Question();
			q.setSurveyId(1L);
			q.setTitle("test survey");
			q.setOrder(1);
		    Long id = dao.save(q);
		    assert(id != null);
		}
	}
	
	@Test
	public void update() {
	    Question q = dao.get(1L);
	    if (null != q) {
		    q.setTitle("test survey v2");
		    dao.update(q);
		    q = dao.get(q.getId());
		    assertEquals("test survey v2", q.getTitle());
	    }
	}
	
	@Test
	public void delete() {
		if (null != dao.get(1L))
			dao.delete(1L);
	}

}
