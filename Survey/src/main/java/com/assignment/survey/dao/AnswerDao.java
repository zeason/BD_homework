package com.assignment.survey.dao;

import java.util.List;

import com.assignment.survey.bean.Answer;

public interface AnswerDao extends GenericDao<Answer,Long>{
	Answer getAnswer(Long userId, Long questionId);
}
