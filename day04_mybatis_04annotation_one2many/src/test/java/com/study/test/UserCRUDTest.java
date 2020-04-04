package com.study.test;

import com.study.dao.IUserDao;
import com.study.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public  void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println("-----每个用户的信息-----");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }



    @Test
    public void testFindOne(){
        User user = userDao.findById(49);
        System.out.println(user);
    }

    @Test
    public  void testFindByName(){
        List<User> users = userDao.findUserByName("%mybatis%");
        //List<User> users = userDao.findUserByName("mybatis");
        users.forEach(System.out::println);
    }



}
