package com.study.dao;

import com.study.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中针对crud一共有四个注解：
 * @SELECT,@INSERT,@UDPATE,@DELETE
 */


@CacheNamespace(blocking = true) // 开启二级缓存的使用
public interface IUserDao {

    @Select("select * from user")
    @Results(id="userMap", value = {
            @Result(id=true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(property = "accounts", column = "id", many = @Many(
                        select = "com.study.dao.IAccountDao.findAccountByUid",
                        fetchType = FetchType.LAZY
            ))
    })
    List<User> findAll(); // 查询所有用户


    @Select("select * from user  where id=#{id} ")
    @ResultMap(value = {"userMap"})
    User findById(Integer userId); // 根据id查询用户

    @Select("select * from user where username like #{username} ") //另一种写法
    @ResultMap(value = {"userMap"})
    //@Select("select * from user where username like '%${value}%' ")
    List<User> findUserByName(String username); // 根据用户名模糊查询

}
