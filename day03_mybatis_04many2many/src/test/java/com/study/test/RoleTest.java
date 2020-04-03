package com.study.test;

import com.study.dao.IRoleDao;
import com.study.dao.IUserDao;
import com.study.domain.Role;
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

public class RoleTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao roleDao;

    /**
     * 测试初始化
     */
    @Before
    public void init() throws IOException {
        // 1、读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、创建SqlSessionFactory工厂
        SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(in);
        // 3、使用工厂生产一个SqlSession对象
        sqlSession = factory.openSession();
        // 4、使用SqlSession创建Dao接口的代理对象
        roleDao = sqlSession.getMapper(IRoleDao.class);
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

    @Test
    public void testFindAll() throws Exception {
        // 5、使用代理对象执行方法
        List<Role> roles = roleDao.findAll();
        for (Role role : roles){
            System.out.println("-----每个角色信息-----");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }





}
