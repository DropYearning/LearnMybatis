package com.study.test;

import com.study.dao.IUserDao;
import com.study.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws Exception {

        /**
         * 测试给基于注解的Mybatis
         */
        // 1、获取字节输入流
        InputStream in = org.apache.ibatis.io.Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2 根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3 根据SqlSessionFactory生产一个SqlSession
        SqlSession session = factory.openSession();
        // 4 使用SqlSession获取Dao的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 5 执行Dao的方法
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }
        // 6 释放资源
        session.close();
        in.close();

    }
}
