package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> findAllUsers(Integer page,Integer rows,String sidx,String sord);
    List<User> findAll();
    void updateUserStatus(User user);
    List<UserCount> findByConditions(String sex);
}
