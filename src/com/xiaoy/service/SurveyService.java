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

	/**
	 * 根据调查的id，清除答案
	 * 
	 * @param sid
	 */
	void clearAnswer(Integer sid);

	/**
	 * 根据调查的id，切换调查的状态
	 * 
	 * @param sid
	 */
	void toggleStatus(Integer sid);

	/**
	 * 更新Logo图片的路径
	 * @param sid
	 * @param string
	 */
	void updateLogoPhoto(Integer sid, String string);
}
