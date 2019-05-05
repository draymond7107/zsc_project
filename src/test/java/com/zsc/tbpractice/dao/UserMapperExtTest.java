package com.zsc.tbpractice.dao;

import com.alibaba.fastjson.JSONObject;
import com.zsc.base.abs.BaseController;
import com.zsc.base.http.HttpResult;
import com.zsc.base.http.HttpUtils;
import com.zsc.general.entity.Teacher;
import com.zsc.general.entity.User;
import com.zsc.general.entity.UserTeacher;
import com.zsc.tbpractice.http.dingding.HttpHelper;
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
public class UserMapperExtTest extends BaseController {
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


    @Test
    public void test() throws Exception {

        String url = "https://ygh.nbgh.gov.cn/testygh-app/oauth/accessToken?clientId=65f9932ec89e4de888674125f87128ce&clientSecret=c1d2f751cdac4c22a40a169fdc9df060";
//"access_token" -> "fc3b7ed676a1191c77eba0277caa1083"

        JSONObject jsonObject = HttpHelper.doGet(url);

        System.out.println(jsonObject);
    }
}