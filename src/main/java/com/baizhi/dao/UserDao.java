package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface UserDao extends Mapper<User> {
    void updateByExampleSelective(Example example);
    List<UserCount> selectByConditions(String sex);
}
