package com.xiaoy.action.form;

import java.util.Date;

public class SurveyForm
{
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getPreText()
	{
		return preText;
	}

	public void setPreText(String preText)
	{
		this.preText = preText;
	}

	public String getNextText()
	{
		return nextText;
	}

	public void setNextText(String nextText)
	{
		this.nextText = nextText;
	}

	public String getExitText()
	{
		return exitText;
	}

	public void setExitText(String exitText)
	{
		this.exitText = exitText;
	}

	public String getDoneText()
	{
		return doneText;
	}

	public void setDoneText(String doneText)
	{
		this.doneText = doneText;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}
