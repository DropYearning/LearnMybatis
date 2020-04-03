package com.study.dao;

import com.study.domain.QueryVo;
import com.study.domain.User;
import org.apache.ibatis.annotations.Select;

import javax.management.Query;
import java.util.List;

public interface IUserDao {

    List<User> findAll(); // 读取所有用户方法

    User findById(Integer userId); // 按用户id查找

    List<User> findByName(String username); // 用名字模糊查找


    List<User> findUserByVo(QueryVo vo); // 根据queryVo中的条件查询用户

    /**
     *
     * @param user 查询的条件，有可能有用户名，有可能有性别，有可能都有...
     * @return
     */
    List<User> findUserByCondition(User user); //根据传入的参数条件查询

    List<User> findUserInIds(QueryVo vo); //子查询，根据QueryVo中提供的id集合查询用户信息

}
