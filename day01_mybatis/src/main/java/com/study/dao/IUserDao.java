package com.study.dao;

import com.study.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    // 也可以用过注解的方式配置，注解可以替代IUserDao.xml
    @Select("select * from user ")
    List<User> findAll();

}
