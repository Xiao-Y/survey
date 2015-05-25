package com.xiaoy.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.AnswerDao;
import com.xiaoy.entities.Answer;
import com.xiaoy.entities.Survey;

@Repository("answerDao")
public class AnswerDaoImpl extends BasicDaoImpl<Answer> implements AnswerDao
{

	@Override
	public void clearAnswer(Survey survey)
	{
		String hql = "delete from Answer a where a.survey = ?";
		this.batchEntityByHQL(hql, survey);
	}

}
