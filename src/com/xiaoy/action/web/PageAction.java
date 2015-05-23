package com.xiaoy.action.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoy.basic.action.BasicAction;
import com.xiaoy.entities.Page;
import com.xiaoy.entities.Survey;
import com.xiaoy.service.PageService;

@Controller
@Scope("prototype")
public class PageAction extends BasicAction<Page>
{
	private static final long serialVersionUID = -8433714783528131890L;

	// Survey的主键
	private Integer sid;
	
	private Integer pid;

	@Resource
	private PageService pageService;

	/**
	 * 进入添加Page页面
	 * 
	 * @return
	 */
	public String toAddPage()
	{
		return "addPagePage";
	}
	
	/**
	 * 编辑Page页面
	 * 
	 * @return
	 */
	public String toEditPage()
	{
		this.model = pageService.getEntity(pid);
		return "editPagePage";
	}

	/**
	 * 保存或更新页面
	 * @return
	 */
	public String saveOrUpdatePage()
	{
		Survey s = new Survey();
		s.setId(sid);
		model.setSurvey(s);
		pageService.saveOrUpdateEntity(model);

		return "designSurveyAction";
	}

	/********************** getter and setter **********************************************/
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
