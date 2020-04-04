package com.study.dao;

import com.study.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {

    // 建立查询的映射
    @Select("select * from account")
    @Results(id="accountMap", value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            /*配置一对一查询 + 立即查询*/
            @Result(property = "user", column = "uid", one = @One(select="com.study.dao.IUserDao.findById", fetchType = FetchType.EAGER) )

    })
    List<Account> findAll(); // 查询所有账户，并且同时输出账户所属用户的信息（使用注解）


    @Select("select * from account where uid = #{uId}")
    List<Account> findAccountByUid(Integer uid); // 根据用户id查询用户信息
}
