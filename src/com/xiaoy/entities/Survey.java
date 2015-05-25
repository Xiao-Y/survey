package com.xiaoy.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 调查类
 * 
 * @author XiaoY
 * @date: 2015年5月16日 下午10:55:24
 */
public class Survey
{
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime = new Date();
	
	//调查状态是否关闭
	private Boolean closed;
	
	//logo图片的路径
	private String logoPhotoPath;

	// 建立从survey到user的对一的关联关系
	private User user;

	// 建立从Survey到Page之间一对多关联关系
	private Set<Page> pages = new HashSet<Page>();

	public Set<Page> getPages()
	{
		return pages;
	}

	public void setPages(Set<Page> pages)
	{
		this.pages = pages;
	}

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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Boolean getClosed()
	{
		return closed;
	}

	public void setClosed(Boolean closed)
	{
		this.closed = closed;
	}

	public String getLogoPhotoPath()
	{
		return logoPhotoPath;
	}

	public void setLogoPhotoPath(String logoPhotoPath)
	{
		this.logoPhotoPath = logoPhotoPath;
	}
}
