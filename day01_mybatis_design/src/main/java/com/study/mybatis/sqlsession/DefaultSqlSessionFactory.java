package com.study.mybatis.sqlsession;

import com.study.mybatis.cfg.Configuration;

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements  SqlSessionFactory{

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库对象（连接）
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
