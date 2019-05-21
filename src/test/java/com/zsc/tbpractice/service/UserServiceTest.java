package com.zsc.tbpractice.service;

import com.zsc.general.entity.User;
import com.zsc.general.entity.UserTeacher;
import com.zsc.tbpractice.entityvo.UserTeacherVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceTest {
    @Autowired
    private UserService userService;

    /**
     * collection查询
     * 多对多
     */
    @Test
    public void selectUserTeacherByUserIdTest() {
        UserTeacherVo userTeacherVo = userService.selectUserTeacherByUserId(1);

        System.out.println(userTeacherVo);
    }

    /**
     * 批量插入
     */
    @Test
    public void insertUserTeacherList() {
        List<UserTeacher> userTeacherList = new ArrayList<>();
        UserTeacher userTeacher1 = new UserTeacher();
        userTeacher1.setUserId(1);
        userTeacher1.setTeacherId(1);
        userTeacherList.add(userTeacher1);
        UserTeacher userTeacher2 = new UserTeacher();
        userTeacher2.setUserId(1);
        userTeacher2.setTeacherId(1);
        userTeacherList.add(userTeacher2);




        Integer integer = userService.insertUserList(userTeacherList);
        System.out.println(integer);
    }

    /**
     * 批量查询
     */
    @Test
    public void selectUserByIdList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<User> userList = userService.selectUserByIdList(list);

        if (userList != null && userList.size() > 0) {
            for (User user :
                    userList) {
                System.out.println(user.toString());

            }
        }

    }
}