package com.study.dao;

import com.study.domain.Role;

import java.util.List;

public interface IRoleDao {

    List<Role> findAll(); // 查询所有角色
}
