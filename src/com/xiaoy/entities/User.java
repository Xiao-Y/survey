package com.xiaoy.entities;

import java.util.Date;

/**
 * 用户类
 * 
 * @author XiaoY
 * @date: 2015年5月16日 下午10:53:54
 */
public class User
{
	private Integer id;
	private String email;
	private String name;
	private String password;
	private String nickName;
	// 注册时间
	private Date regDate = new Date();
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public Date getRegDate()
	{
		return regDate;
	}

	public void setRegDate(Date regDate)
	{
		this.regDate = regDate;
	}
}