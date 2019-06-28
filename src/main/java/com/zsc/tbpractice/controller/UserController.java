package com.zsc.tbpractice.controller;

import com.github.pagehelper.PageInfo;
import com.zsc.base.abs.BaseController;
import com.zsc.base.spring.JsonResult;
import com.zsc.general.entity.Admin;
import com.zsc.general.entity.User;
import com.zsc.tbpractice.queryvo.BaseQuery;
import com.zsc.tbpractice.service.UserService;
import com.zsc.tbpractice.entityvo.UserTeacherVo;
import com.zsc.utils.UserUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */
@CrossOrigin
@RequestMapping("adm/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 单表查询user
     *
     * @param baseQuery
     * @return
     */
    @RequestMapping("/selectUserPageList")
    public JsonResult selectUserPageList(BaseQuery baseQuery) {

        PageInfo<Admin> adminPageInfo = userService.selectUserPageList(baseQuery);

        return sendSuccess(adminPageInfo);
    }

    @RequestMapping("/selectUserTeacherByUserId")
    public JsonResult selectUserTeacherByUserId(Integer userId) {
        if (userId == null) {
            return sendError("请选择一条记录");
        }
        UserTeacherVo userTeacherVo = userService.selectUserTeacherByUserId(userId);
        return sendSuccess(userTeacherVo);
    }

    /**
     * 返回主键，自动set到对应的值
     *
     * @param user
     * @return
     */
    @Transactional  //事务演示
    @RequestMapping("/insertUserInfo")
    public JsonResult insertUserInfo(User user) {

        userService.insertUserInfo(user);
        //   int i = 1 / 0;

        return sendSuccess(null);
    }


    public JsonResult insertUser() {


        int i = 1;
        while (i <= 100) {
            User user = new User();
            user.setUserName(UserUtils.getChineseName());
            user.setTrueName(UserUtils.getChineseName());
            Random random = new Random();
            int classId = random.nextInt(100);
            user.setClassId(classId);
            user.setPassword("");
            user.setAge(random.nextInt(80));
            int i1 = random.nextInt(10);
            if (i1 % 2 == 0) {
                user.setSex("M");
            } else {
                user.setSex("F");
            }



        }

        return sendSuccess();
    }

}