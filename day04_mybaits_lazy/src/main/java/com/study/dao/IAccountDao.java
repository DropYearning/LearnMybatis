package com.study.dao;

import com.study.domain.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll(); // 查询所有账户，同时输出账户所属用户的所有信息（一对一查询）

    List<Account> findAccountByUid(Integer uid); //根据用户id查询账户信息
}
