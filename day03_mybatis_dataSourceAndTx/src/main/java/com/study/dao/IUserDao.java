package com.study.dao;

import com.study.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll(); // 读取所有用户方法

    void saveUser(User user); // 保存用户方法

    void updateUser(User user); // 更新用户

    void deleteUser(Integer userId); // 删除用户

    User findById(Integer userId); // 按用户id查找

    List<User> findByName(String username); // 用名字模糊查找

    int findTotal(); // 查询总用户数


}
