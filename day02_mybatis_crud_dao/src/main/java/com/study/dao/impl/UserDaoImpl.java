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

    @Override
    public List<User> findAll() {
        // 1 从factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2 调用session中的方法实现查询列表
        List<User> users = session.selectList("com.study.dao.IUserDao.findAll"); // 能获取到配置信息的key
        // 3 释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        // 1 从factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2 调用session中的方法
        session.insert("com.study.dao.IUserDao.saveUser", user); // 参数1：用哪一个语句执行；后面是需要的参数
        session.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        // 1 从factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2 调用session中的方法
        session.update("com.study.dao.IUserDao.updateUser", user); // 参数1：用哪一个语句执行；后面是需要的参数
        session.commit();
        session.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        // 1 从factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2 调用session中的方法
        session.delete("com.study.dao.IUserDao.deleteUser", userId); // 参数1：用哪一个语句执行；后面是需要的参数
        session.commit();
        session.close();

    }

    @Override
    public User findById(Integer userId) {
        // 1 从factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2 调用session中的方法实现查询列表
        User user = session.selectOne("com.study.dao.IUserDao.findById", userId); // 能获取到配置信息的key
        // 3 释放资源
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String username) {
        // 1 从factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2 调用session中的方法实现查询列表
        List<User> users = session.selectList("com.study.dao.IUserDao.findByName", username); // 能获取到配置信息的key
        // 3 释放资源
        session.close();
        return users;
    }

    @Override
    public int findTotal() {
        // 1 从factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2 调用session中的方法实现查询列表
        Integer count =  session.selectOne("com.study.dao.IUserDao.findTotal");
        return count;
    }
}
