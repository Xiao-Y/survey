package com.xiaoy.action.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Page;
import com.xiaoy.entities.Question;
import com.xiaoy.service.QuestionService;

@Controller
@Scope("prototype")
public class QuestionAction extends BasicAction<Question>
{
	private static final long serialVersionUID = 5470553757180218630L;

	private Integer sid;

	private Integer pid;
	
	@Resource
	private QuestionService questionService;

	/**
	 * 进入选择问题类型页面
	 * 
	 * @return
	 */
	public String toSelectQuestionType()
	{
		return "selectQuestionTypePage";
	}

	/**
	 * 进入问题添加页面
	 * 
	 * @return
	 */
	public String toDesignQuestionPage()
	{
		return model.getQuestionType() + "";
	}

	/**
	 * 保存更新问题页面
	 * 
	 * @return
	 */
	public String saveOrUpdateQuestion()
	{
		Page page = new Page();
		page.setId(pid);
		model.setPage(page);
		questionService.saveOrUpdateEntity(model);
		return "designSurveyAction";
	}

	/****************** getter and setter **********************/

	public Integer getSid()
	{
		return sid;
	}

	public void setSid(Integer sid)
	{
		this.sid = sid;
	}

	public Integer getPid()
	{
		return pid;
	}

	public void setPid(Integer pid)
	{
		this.pid = pid;
	}
}
