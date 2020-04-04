package com.study.test;

import com.study.dao.IAccountDao;
import com.study.dao.IUserDao;
import com.study.domain.Account;
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
import java.util.List;

public class SecondLevelCacheTest {
    private InputStream in;
    private SqlSessionFactory factory;


    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After
    public void destroy() throws IOException {
        in.close();
    }

    @Test
    public void testFindOne(){
        SqlSession  session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        User user = userDao.findById(49);
        System.out.println(user);
        session.close(); //先释放一级缓存

        SqlSession session1 = factory.openSession();
        IUserDao userDao1 = session1.getMapper(IUserDao.class);
        User user1 = userDao1.findById(49);
        System.out.println(user1);
        session1.close();
    }


}
