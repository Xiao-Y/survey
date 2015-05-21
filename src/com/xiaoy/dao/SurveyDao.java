package com.xiaoy.dao;

import java.util.List;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;

public interface SurveyDao extends BasicDao<Survey>
{

	/**
	 * 查询我的调查列表
	 * @param user
	 * @return
	 */
	List<Survey> findMySurveys(User user);

}
