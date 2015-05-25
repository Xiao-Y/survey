package com.xiaoy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.impl.BasicServiceImpl;
import com.xiaoy.dao.AnswerDao;
import com.xiaoy.dao.PageDao;
import com.xiaoy.dao.SurveyDao;
import com.xiaoy.entities.Page;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;
import com.xiaoy.service.SurveyService;

@Service("surveyService")
public class SurveyServiceImpl extends BasicServiceImpl<Survey> implements SurveyService
{
	private SurveyDao surveyDao;

	@Override
	@Resource(name = "surveyDao")
	public void setBasicDao(BasicDao<Survey> basicDao)
	{
		super.basicDao = basicDao;
		this.surveyDao = (SurveyDao) basicDao;
	}

	@Resource(name = "pageDao")
	private PageDao pageDao;

	@Resource(name = "answerDao")
	private AnswerDao answerDao;

	@Override
	public List<Survey> findMySurveys(User user)
	{
		return surveyDao.findMySurveys(user);
	}

	@Override
	public Survey newSurvey(User user)
	{
		Survey survey = new Survey();
		Page page = new Page();

		survey.setUser(user);
		page.setSurvey(survey);
		survey.getPages().add(page);

		surveyDao.saveEntity(survey);
		pageDao.saveEntity(page);

		return survey;
	}

	@Override
	public void clearAnswer(Integer sid)
	{
		Survey survey = new Survey();
		survey.setId(sid);

		answerDao.clearAnswer(survey);
	}

	@Override
	public void toggleStatus(Integer sid)
	{
		surveyDao.toggleStatus(sid);
	}

	@Override
	public void updateLogoPhoto(Integer sid, String logoPhotoPath)
	{
		Survey survey = surveyDao.getEntity(sid);
		survey.setLogoPhotoPath(logoPhotoPath);
	}
}
