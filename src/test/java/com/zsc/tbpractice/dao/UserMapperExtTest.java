package com.zsc.tbpractice.dao;

import com.zsc.general.entity.Teacher;
import com.zsc.general.entity.User;
import com.zsc.general.entity.UserTeacher;
import com.zsc.tbpractice.vo.MapVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserMapperExtTest {
    @Autowired
    private UserMapperExt userMapperExt;

    @Test
    public void selectUserByUserIdListTest() {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        Map<Integer, MapVo> userMap = userMapperExt.selectUserByUserIdList(list);


        Iterator<Map.Entry<Integer, MapVo>> iterator = userMap.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Integer, MapVo> next = iterator.next();

            System.out.println("Key = " + next.getKey() + ", Value = " + next.getValue());

        }

    }

    @Test
    public void selectUserByIdSetTest() {

        Set<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.add(2);
        List<User> userList = userMapperExt.selectUserListByIdSet(integers);

        for (User user :
                userList) {
            System.out.println(user);
        }

    }

    @Test
    public void selectUserByIdSetResultMapTest() {

        Set<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.add(2);
        Map<Integer, User> userMap = userMapperExt.selectUserByIdSetResultMap(integers);

        Iterator<Map.Entry<Integer, User>> iterator = userMap.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Integer, User> next = iterator.next();

            System.out.println("Key = " + next.getKey() + ", Value = " + next.getValue());

        }

    }

    @Test
    public void insertTeacherListTest() {

        List<UserTeacher> teachList = new ArrayList<>();
        UserTeacher userTeacher1 = new UserTeacher();
        userTeacher1.setUserId(5);
        userTeacher1.setTeacherId(5);

        UserTeacher userTeacher2 = new UserTeacher();
        userTeacher2.setUserId(5);
        userTeacher2.setTeacherId(6);

        teachList.add(userTeacher1);
        teachList.add(userTeacher2);

        Integer integer = userMapperExt.insertTeacherList(teachList);
        System.out.println(integer);


    }
}