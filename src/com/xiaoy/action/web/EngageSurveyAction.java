package com.xiaoy.action.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Page;
import com.xiaoy.entities.Survey;
import com.xiaoy.service.PageService;
import com.xiaoy.service.SurveyService;
import com.xiaoy.util.StringUtil;
import com.xiaoy.util.ValiDateUtil;

/**
 * 参与调查
 * 
 * @author XiaoY
 * @date: 2015年5月26日 下午5:41:44
 */
@Controller
@Scope("prototype")
public class EngageSurveyAction extends BasicAction<Survey> implements ServletContextAware, ParameterAware
{
	private static final long serialVersionUID = -8032228011610142602L;

	// 当前调查的key
	public final static String CURRENT_SURVEY = "current_survey";
	// 存放用户的答案
	public final static String ALL_PARAMS_MAP = "all_params_map";

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

	// 当前页的id
	private Integer currPid;

	// 接收所有参数的map
	private Map<String, String[]> paramMap;

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
		// 存放survey到session
		this.sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());

		// 存放用户的答案，用于回显
		this.sessionMap.put(ALL_PARAMS_MAP, new HashMap<Integer, Map<String, String[]>>());
		return "engageSurveyPage";
	}

	/**
	 * 处理参与调查
	 * 
	 * @return
	 */
	public String doEngageSurvey()
	{
		// 获取提交按钮的名称
		String submitName = this.getSubmitName();
		// 上一页
		if (submitName.endsWith("pre"))
		{
			// 合并参数到session中
			this.mergeParamsIntoSession();
			// 查询当前页的上一页
			this.currPage = pageService.getPrePage(currPid);
			return "engageSurveyPage";

		} else if (submitName.endsWith("next"))// 下一页
		{
			this.mergeParamsIntoSession();
			// 查询当前页的下一页
			this.currPage = pageService.getNextPaeg(currPid);
			return "engageSurveyPage";

		} else if (submitName.endsWith("done"))// 完成
		{
			this.mergeParamsIntoSession();
			// TODO答案入库
			// 清除Session
			this.clearSessionData();
			return "engageSurveyAction";

		} else if (submitName.endsWith("exit"))
		{// 退出
			// 清除Session
			this.clearSessionData();
			return "engageSurveyAction";
		}
		return null;
	}

	/**
	 * 清除Session
	 */
	private void clearSessionData()
	{
		sessionMap.remove(ALL_PARAMS_MAP);
		sessionMap.remove(CURRENT_SURVEY);
	}

	/**
	 * 合并参数到session中
	 */
	private void mergeParamsIntoSession()
	{
		// 获取session中存放的所有参数
		Map<Integer, Map<String, String[]>> allParamsMap = this.getAllParamsMap();
		allParamsMap.put(currPid, this.paramMap);
	}

	/**
	 * 获取session中存放的所有参数
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMap()
	{
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAMS_MAP);
	}

	/**
	 * 获取提交按钮的名称
	 * 
	 * @return
	 */
	private String getSubmitName()
	{
		for (String key : paramMap.keySet())
		{
			if (key.startsWith("submit_"))
			{
				return key;
			}
		}
		return null;
	}

	/**
	 * 设置标记，用于答案的回显。主要用于：checked、selected、radio
	 * 
	 * @param name
	 *            参数的名称
	 * @param value
	 *            参数的值
	 * @param tag
	 *            标签的类型
	 * @return
	 */
	public String setTag(String name, String value, String selTag)
	{
		Map<String, String[]> map = this.getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		if (StringUtil.isContains(values, value))
		{
			return selTag;
		}
		return "";
	}

	/**
	 * 设置标记，用于答案的回显。主要用于：text
	 * 
	 * @param name
	 *            参数的名称
	 * @param value
	 *            参数的值
	 * @param tag
	 *            标签的类型
	 * @return
	 */
	public String setText(String name)
	{
		Map<String, String[]> map = this.getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		return "value='" + values[0] + "'";
	}

	/************* getter and setter *************************/

	@Override
	public void setParameters(Map<String, String[]> paramMap)
	{
		this.paramMap = paramMap;
	}

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

	public Integer getCurrPid()
	{
		return currPid;
	}

	public void setCurrPid(Integer currPid)
	{
		this.currPid = currPid;
	}
}
