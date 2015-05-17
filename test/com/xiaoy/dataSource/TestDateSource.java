package com.xiaoy.dataSource;

import org.apache.log4j.Logger;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDateSource
{
	private static final Logger logger = Logger.getLogger(TestDateSource.class);

	ApplicationContext ac = new ClassPathXmlApplicationContext("spring-hibernate-c3p0.xml");

	@Test
	public void testDateSource()
	{
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		logger.info(dataSource);

	}
}
