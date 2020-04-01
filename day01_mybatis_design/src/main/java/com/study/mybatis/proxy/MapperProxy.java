package com.study.mybatis.proxy;

import com.study.mybatis.cfg.Mapper;
import com.study.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    // map的key是全限定类名 + 方法名
    private Map<String, Mapper> mappers = new HashMap<String, Mapper>(); // 必须要有其中的SQL语句
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 用于对方法进行增强，我们的增加其实调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 1、获取方法名
        String methodName = method.getName();
        // 2、获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        // 3 组合key
        String key = className + "." + methodName;
        // 4 获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);
        // 5 判断一下是否有Mapper
        if(mapper == null){
            throw new IllegalArgumentException("传入的参数有误");
        }
        // 6 调用工具类执行查询所有
        return new Executor().selectList(mapper, conn);
    }
}
