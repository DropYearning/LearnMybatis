package com.study.test;

import com.study.dao.IUserDao;
import com.study.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
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
        SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(in);
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

    @Test
    public void testFindAll() throws Exception {
        // 5、使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
    }


    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById() {
        // 5、使用代理对象执行方法
        User user = userDao.findById(46);
        System.out.println(user);
    }

    /**
     * 测试根据名字模糊查询
     */
    @Test
    public void testFindByName() {
        // 5、使用代理对象执行方法
        // 这种方法将模糊查询的百分号写在调用处
        //List<User> users = userDao.findByName("%王");
        List<User> users = userDao.findByName("王");
        for (User user : users){
            System.out.println(user);
        }
    }


    /**
     * 测试使用queryVo作为查询条件
     */
    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        //user.setUsername("王"); // 不模糊
        user.setUsername("%王"); // 模糊
        vo.setUser(user);
        // 5、使用代理对象执行方法
        // 这种方法将模糊查询的百分号写在调用处
        //List<User> users = userDao.findByName("%王");
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users){
            System.out.println(u);
        }
    }

    /**
     * 测试条件查询
     */
    @Test
    public void testFindByCondition(){
        User u = new User();
        u.setUsername("老王");
        u.setSex("女");
        List<User> users = userDao.findUserByCondition(u);
        for (User user : users){
            System.out.println(user);
        }
    }


    /**
     * 测试子查询foreach标签的使用
     */
    @Test
    public void testFindUserInIds(){
        QueryVo queryVo = new QueryVo();
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(42);
        list.add(43);
        list.add(44);
        queryVo.setIds(list);

        List<User> users = userDao.findUserInIds(queryVo);
        for (User user : users){
            System.out.println(user);
        }
    }
}
