package com.xiaoy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.impl.BasicServiceImpl;
import com.xiaoy.dao.AnswerDao;
import com.xiaoy.dao.PageDao;
import com.xiaoy.dao.QuestionDao;
import com.xiaoy.dao.SurveyDao;
import com.xiaoy.entities.Page;
import com.xiaoy.entities.Question;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;
import com.xiaoy.service.SurveyService;
import com.xiaoy.util.DataHelp;

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

	@Resource(name = "questionDao")
	private QuestionDao questionDao;

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

	@Override
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos)
	{
		Page srcPage = pageDao.getEntity(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page targPage = pageDao.getEntity(targPid);
		Survey targSurvey = targPage.getSurvey();
		// 判断移动/赋值
		if (srcSurvey.getId().equals(targSurvey.getId()))
		{
			setOrderno(srcPage, targPage, pos);
		}
		// 复制
		else
		{
			// 强行初始化问题集合,否则深度复制的页面对象没有问题集合
			srcPage.getQuestions().size();
			// 深度复制
			Page copyPage = (Page) DataHelp.deeplyCopy(srcPage);
			// 设置页面和目标调查关联
			copyPage.setSurvey(targSurvey);
			// 保存页面
			pageDao.saveEntity(copyPage);
			for (Question q : copyPage.getQuestions())
			{
				questionDao.saveEntity(q);
			}
			setOrderno(copyPage, targPage, pos);
		}
	}

	/**
	 * 设置页序
	 */
	private void setOrderno(Page srcPage, Page targPage, int pos)
	{
		// 之前
		if (pos == 0)
		{
			// 判断目标页是否是首页
			if (isFirstPage(targPage))
			{
				srcPage.setOrderno(targPage.getOrderno() - 0.01f);
			} else
			{
				// 取得目标页的前一页
				Page prePage = getPrePage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + prePage.getOrderno()) / 2);
			}
		}
		// 之后
		else
		{
			// 判断目标页是否是尾页
			if (isLastPage(targPage))
			{
				srcPage.setOrderno(targPage.getOrderno() + 0.01f);
			} else
			{
				// 取得目标页的下一页
				Page nextPage = getNextPage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + nextPage.getOrderno()) / 2);
			}
		}
	}

	/**
	 * 查询指定页面的下一页
	 */
	private Page getNextPage(Page targPage)
	{
		List<Page> list = pageDao.getNextPage(targPage);
		return list.get(0);
	}

	/**
	 * 判断指定页面是否是所在调查尾页
	 */
	private boolean isLastPage(Page targPage)
	{
		Long count = (Long) pageDao.isLastPage(targPage);
		return count == 0;
	}

	/**
	 * 查询指定页面的上一页
	 */
	private Page getPrePage(Page targPage)
	{
		List<Page> list = pageDao.getPrePage(targPage);
		return list.get(0);
	}

	/**
	 * 判断指定页面是否是所在调查首页
	 */
	private boolean isFirstPage(Page targPage)
	{
		Long count = (Long) pageDao.isFirstPage(targPage);
		return count == 0;
	}
}
