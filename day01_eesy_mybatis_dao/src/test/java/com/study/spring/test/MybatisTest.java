package com.study.spring.test;


import com.study.dao.impl.UserDaoImpl;
import com.study.domain.User;
import com.study.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis入门案例
 */
public class MybatisTest {
    public static void main(String[] args) throws Exception {
        // 1、读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、创建SqlSessionFactory工厂（
        // TODO：Builder:建造者模式？
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3、使用工厂创建Dao的对象
        IUserDao userDao = new UserDaoImpl(factory);
        // 4、使用对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
        // 5、释放方法
        in.close();
    }
}
