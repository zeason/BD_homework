package com.assignment.survey.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.survey.bean.Option;
import com.assignment.survey.bean.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml", "classpath:META-INF/spring-mvc.xml"})
@Transactional
public class OptionDaoTest {
	@Autowired
	private OptionDao dao;
	@Autowired
	private QuestionDao questionDao;
	
	@Test
	public void getByQuestionId() {
		List<Option> list = dao.getByQuestionId(1L);
		System.out.println(list);
	}
	
	@Test
	public void get() {
	    Option o = dao.get(1L);
	    System.out.println(o);
	}
	
	@Test
	public void save() {
		Question question = questionDao.get(1L);
		if (null != question) {
			Option o = new Option();
			o.setQuestionId(1L);
			o.setContent("test survey");
			o.setOrder(1);
		    Long id = dao.save(o);
		    assert(id != null);
		}
	}
	
	@Test
	public void update() {
	    Option o = dao.get(1L);
	    if (null != o) {
		    o.setContent("test survey v2");
		    dao.update(o);
		    o = dao.get(o.getId());
		    assertEquals("test survey v2", o.getContent());
	    }
	}
	
	@Test
	public void delete() {
		if (null != dao.get(1L))
			dao.delete(1L);
	}

}
