package com.assignment.survey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.survey.bean.Survey;
import com.assignment.survey.dao.SurveyDao;
import com.assignment.survey.service.SurveyService;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Autowired  
    private SurveyDao dao;
	
	@Override
	public Survey get(Long id) {
		return dao.get(id);
	}

	@Override
	public Long save(Survey entity) {
		return dao.save(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);

	}

	@Override
	public List<Survey> getByCreator(Long id) {
		return dao.getByCreator(id);
	}

	@Override
	public List<Survey> getAnswered(Long userId) {
		return dao.getAnswered(userId);
	}

	@Override
	public boolean isAnswered(Long id) {
		return dao.isAnswered(id);
	}

	@Override
	public void update(Survey survey) {
		dao.update(survey);
	}

}
