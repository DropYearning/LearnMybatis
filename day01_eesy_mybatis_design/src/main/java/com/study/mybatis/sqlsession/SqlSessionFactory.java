package com.study.mybatis.sqlsession;

public interface SqlSessionFactory {
    /**
     * 用于打开创建一个SqlSession对象
     * @return
     */
    SqlSession openSession();

}
