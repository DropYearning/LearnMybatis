package com.study.dao.impl;

import com.study.dao.IUserDao;
import com.study.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        // 1、使用工厂创建SqlSession
        SqlSession session = factory.openSession();
        // 2、使用session执行查询所有方法
        // 光靠id无法找到xml中对应的sql语句，一定要加上xml中声明的namespace
        List<User> users = session.selectList("com.study.dao.IUserDao.findAll");
        session.close();
        //3、返回查询结果
        return users;
    }
}
