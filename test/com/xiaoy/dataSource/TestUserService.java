package com.xiaoy.dataSource;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaoy.entities.User;
import com.xiaoy.service.UserService;

public class TestUserService
{
	ApplicationContext ac = new ClassPathXmlApplicationContext("spring-hibernate-c3p0.xml");

	@Test
	public void testUserService()
	{
		UserService userService = (UserService) ac.getBean("userService");
		User t = new User();
		t.setEmail("xiaoy@.qq.com");
		t.setName("XiaoY");
		t.setNickName("yyy");
		t.setPassword("123");
		t.setRegDate(new Date());
		userService.saveEntity(t);
		
	}
}
