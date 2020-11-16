package com.assignment.survey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.survey.bean.Question;
import com.assignment.survey.dao.QuestionDao;
import com.assignment.survey.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired  
    private QuestionDao dao;
	
	@Override
	public Question get(Long id) {
		return dao.get(id);
	}

	@Override
	public Long save(Question entity) {
		return dao.save(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);

	}

	@Override
	public List<Question> getBySurveyId(Long id) {
		return dao.getBySurveyId(id);
	}

}
