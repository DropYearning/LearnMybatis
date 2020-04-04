package com.study.dao;

import com.study.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 在mybatis中针对crud一共有四个注解：
 * @SELECT,@INSERT,@UDPATE,@DELETE
 */
public interface IUserDao {

    @Select("select * from user")
    List<User> findAll(); // 查询所有用户

    @Insert("insert into user(username, address, sex, birthday) values (#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user); // 保存用户

    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(User user); // 更新用户

    @Delete("delete from user where id=#{id} ")
    void deleteUser(Integer userId); //删除用户

    @Select("select * from user  where id=#{id} ")
    User findById(Integer userId); // 根据id查询用户

    //    @Select("select * from user where username like #{username} ") //另一种写法
    @Select("select * from user where username like '%${value}%' ")
    List<User> findUserByName(String username); // 根据用户名模糊查询

    @Select("select count(*) from user ")
    int findTotalUser(); // 查询总用户数

}
