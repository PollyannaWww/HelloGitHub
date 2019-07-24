package com.baizhi;

import com.baizhi.entity.UserCount;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;
    @Test
    public void test1(){
        List<UserCount> list = userService.findByConditions("男");
        list.forEach(l -> System.out.println(l));
    }
}
