package com.zsc.tbpractice.controller;

import com.zsc.base.spring.JsonResult;
import com.zsc.tbpractice.general.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void insertUserInfoTest() {
        User user = new User();
        user.setAge(12);
        user.setClassId(1);
        user.setTrueName("zzz");
        user.setUserName("zzzzzz");
        userController.insertUserInfo(user);

    }

}