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
import java.util.List;

public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;


    /**
     * 测试初始化
     */
    @Before
    public void init() throws IOException {
        // 1、读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、创建SqlSessionFactory工厂
        factory =  new SqlSessionFactoryBuilder().build(in);
    }


    /**
     * 释放资源
     */
    @After
    public void destroy() throws IOException {
        in.close();
    }


    /**
     * 测试二级缓存
     */
    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession1 = factory.openSession();
        IUserDao dao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findById(41);
        System.out.println(user1);
        sqlSession1.close(); // 释放sqlSession1中的一级缓存
        // sqlSession必须commit()或者close()之后才会将数据保存到二级缓存中

        // sqlSession1和sqlSession2是同一个factory创建出的session对象，共享二级缓存
        SqlSession sqlSession2 = factory.openSession();
        IUserDao dao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findById(41);
        System.out.println(user2);
        sqlSession2.close();

        System.out.println(user1 == user2);
    }






}
