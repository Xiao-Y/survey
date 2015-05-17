package com.xiaoy.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.UserDao;
import com.xiaoy.entities.User;

@Repository("userDao")
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao
{

}
