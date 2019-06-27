package com.github.supertrampai.fantasticfunicular.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-06-11 00:15
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() throws Exception {
       /* User user=new User();
        user.setId(2);
        user.setName("小明");
        user.setPassword("fffooo123");
        userService.saveUser(user);*/
    }

 /*   @Test
    public void findUserByUserName(){
        User user= userService.findUserByUserName("小明");
        System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        User user=new User();
        user.setId(2);
        user.setName("天空");
        user.setPassword("fffxxxx");
        userService.updateUser(user);
    }

    @Test
    public void deleteUserById(){
        userService.deleteUserById(1);
    }*/

}
