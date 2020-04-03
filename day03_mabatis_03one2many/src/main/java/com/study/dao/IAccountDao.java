package com.study.dao;

import com.study.domain.Account;
import com.study.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll(); // 查询所有账户，同时输出账户所属用户的所有信息（一对一查询）


    List<AccountUser> findAllAccount(); // 查询所有账户,同时输出用户名和地址信息
}
