package com.study.test;

import com.study.dao.IAccountDao;
import com.study.dao.IUserDao;
import com.study.domain.Account;
import com.study.domain.AccountUser;
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

public class AccountTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

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
        accountDao = sqlSession.getMapper(IAccountDao.class);
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
     * 查询所有账户，并输出附带的用户信息
     * @throws Exception
     */
    @Test
    public void testFindAll() throws Exception {
        // 5、使用代理对象执行方法
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts){
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 查询所有账户,同时输出用户名和地址信息
     * @throws Exception
     */
    @Test
    public void findAllAccount() throws Exception {
        // 5、使用代理对象执行方法
        List<AccountUser> aus = accountDao.findAllAccount();
        for (AccountUser au : aus){
            System.out.println(au);
        }
    }


}
