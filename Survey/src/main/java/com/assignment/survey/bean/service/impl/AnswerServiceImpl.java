package com.assignment.survey.bean.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.survey.bean.Answer;
import com.assignment.survey.bean.service.AnswerService;
import com.assignment.survey.dao.AnswerDao;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

	@Autowired  
    private AnswerDao dao;
	
	@Override
	public Answer get(Long id) {
		return dao.get(id);
	}

	@Override
	public Long save(Answer entity) {
		return dao.save(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);

	}

	@Override
	public Answer getAnswer(Long userId, Long questionId) {
		return dao.getAnswer(userId, questionId);
	}

}
