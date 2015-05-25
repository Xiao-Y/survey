package com.xiaoy.dao;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.entities.Answer;
import com.xiaoy.entities.Survey;

public interface AnswerDao extends BasicDao<Answer>
{

	/**
	 * 根据调查的id清除答案
	 * @param survey
	 */
	void clearAnswer(Survey survey);

}
