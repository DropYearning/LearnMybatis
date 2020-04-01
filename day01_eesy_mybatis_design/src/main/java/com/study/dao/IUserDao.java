package com.study.dao;

import com.study.domain.User;
import com.study.mybatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    // 使用注解
    @Select("select * from user")
    List<User> findAll();

}
