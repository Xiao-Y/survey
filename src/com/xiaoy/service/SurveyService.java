package com.xiaoy.service;

import java.util.List;

import com.xiaoy.basic.service.BasicService;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;

public interface SurveyService extends BasicService<Survey>
{

	/**
	 * 查询我的调查列表
	 * 
	 * @param user
	 * @return
	 */
	List<Survey> findMySurveys(User user);

	/**
	 * 新建调查
	 * 
	 * @param user
	 * @return
	 */
	Survey newSurvey(User user);

}
