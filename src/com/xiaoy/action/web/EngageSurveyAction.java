package com.xiaoy.action.web;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Page;
import com.xiaoy.entities.Survey;
import com.xiaoy.service.PageService;
import com.xiaoy.service.SurveyService;
import com.xiaoy.util.ValiDateUtil;

/**
 * 参与调查
 * 
 * @author XiaoY
 * @date: 2015年5月26日 下午5:41:44
 */
@Controller
@Scope("prototype")
public class EngageSurveyAction extends BasicAction<Survey> implements ServletContextAware
{
	private static final long serialVersionUID = -8032228011610142602L;

	public final static String CURRENT_SURVEY = "current_survey";

	@Resource
	private SurveyService surveyService;

	@Resource
	private PageService pageService;

	private List<Survey> surveys;

	// 接收ServletContext
	private ServletContext context;

	// 调查的id
	private Integer sid;

	// 当前页面
	private Page currPage;

	/**
	 * 查询出所有的可用的调查
	 * 
	 * @return
	 */
	public String findAllAvailableSurveys()
	{
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}

	/**
	 * 获取图片路径
	 * 
	 * @param path
	 * @return
	 */
	public String getImageUrl(String path)
	{
		if (!ValiDateUtil.isValid(path))
		{
			String absPath = context.getRealPath(path);
			File file = new File(absPath);
			if (file.exists())
			{
				return context.getContextPath() + path;
			}
		}

		return context.getContextPath() + "/upload/question.bmp";
	}

	/**
	 * 首次进入参与调查
	 * 
	 * @return
	 */
	public String entry()
	{
		// 首次参与调查
		this.currPage = pageService.getFirstPage(sid);
		//存放survey到session
		this.sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		return "engageSurveyPage";
	}

	/************* getter and setter *************************/

	public List<Survey> getSurveys()
	{
		return surveys;
	}

	public void setSurveys(List<Survey> surveys)
	{
		this.surveys = surveys;
	}

	// 注入ServletContext
	@Override
	public void setServletContext(ServletContext context)
	{
		this.context = context;
	}

	public Integer getSid()
	{
		return sid;
	}

	public void setSid(Integer sid)
	{
		this.sid = sid;
	}

	public Page getCurrPage()
	{
		return currPage;
	}

	public void setCurrPage(Page currPage)
	{
		this.currPage = currPage;
	}
}
