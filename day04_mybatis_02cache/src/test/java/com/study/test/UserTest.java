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

public class UserTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 测试初始化
     */
    @Before
    public void init() throws IOException {
        // 1、读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、创建SqlSessionFactory工厂
        factory =  new SqlSessionFactoryBuilder().build(in);
        // 3、使用工厂生产一个SqlSession对象
        sqlSession = factory.openSession();
        // 4、使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 释放资源
     */
    @After
    public void destroy() throws IOException {
        // 提交事务
        // 没有提交的事务使用过的自增主键不会再被使用
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }


    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        User user1 = userDao.findById(41);
        System.out.println(user1);

        sqlSession.close();
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);

        // 也可以使用sqlSession.clearCache();来手动清空缓存

        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2); // true
    }


    /**
     * 测试缓存的同步
     */
    @Test
    public void testSyncCache(){
        // 1、根据id查询用户
        User user1 = userDao.findById(41);
        System.out.println(user1);
        // 2、更新该用户信息
        user1.setUsername("update SyncCache");
        user1.setAddress("SyncCache");
        userDao.updateUser(user1);
        // 3、再次查询id为41的用户
        // 当调用SqlSession的**修改，添加，删除，commit()，close()等方法**时，就会清空一级缓存。
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2); // true
    }




}
