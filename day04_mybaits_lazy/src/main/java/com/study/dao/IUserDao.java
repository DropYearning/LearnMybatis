package com.study.dao;

import com.study.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll(); // 读取所有用户方法，同时获取到用户下所有账户的信息（一对多查询）

    User findById(Integer userId); // 按用户id查找



}
