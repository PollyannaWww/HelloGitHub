package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Map<String, Object> findAllUsers(Integer page, Integer rows, String sidx, String sord) {
        Map<String, Object> map = new HashMap<>();
        Example example = new Example(User.class);
        example.setOrderByClause(sidx+" "+sord);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDao.selectByExampleAndRowBounds(example, rowBounds);
        int count = userDao.selectCount(new User());
        map.put("rows",users);
        map.put("sidx",sidx);
        map.put("sord",sord);
        map.put("records",count);
        map.put("total",(count%rows==0)?count/rows:count/rows+1);
        map.put("page",page);
        return map;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public void updateUserStatus(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id",user.getId());
        if("正常".equals(user.getUserStatus())){
            example.createCriteria().andEqualTo("userStatus","冻结");
        }else {
            example.createCriteria().andEqualTo("userStatus","正常");
        }
        userDao.updateByExampleSelective(example);

    }

    @Override
    public List<UserCount> findByConditions(String sex) {
        List<UserCount> userCounts = userDao.selectByConditions(sex);
        return userCounts;
    }
}
