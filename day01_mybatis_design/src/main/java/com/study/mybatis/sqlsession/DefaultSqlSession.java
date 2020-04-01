package com.study.mybatis.sqlsession;

import com.study.mybatis.cfg.Configuration;
import com.study.mybatis.cfg.Mapper;
import com.study.mybatis.proxy.MapperProxy;
import com.study.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession接口的默认实现
 */
public class DefaultSqlSession implements SqlSession{
    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {

        /**
         * Proxy类的newInstance()方法有三个参数：
         * ClassLoader loader：它是类加载器类型，你不用去理睬它，你只需要知道怎么可以获得它就可以了：
         *      MyInterface.class.getClassLoader()就可以获取到ClassLoader对象，
         *      没错，只要你有一个Class对象就可以获取到ClassLoader对象；
         * Class[] interfaces：指定newProxyInstance()方法返回的对象要实现哪些接口，
         *      没错，可以指定多个接口，例如上面例子只我们只指定了一个接口：
         *      Class[] cs = {MyInterface.class};InvocationHandler
         *  h：它是最重要的一个参数！它是一个接口！它的名字叫调用处理器！
         *      无论你调用代理对象的什么方法，它都是在调用InvocationHandler的invoke()方法！
         */
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass}, new MapperProxy(cfg.getMappers(), connection));
    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (connection != null){
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
