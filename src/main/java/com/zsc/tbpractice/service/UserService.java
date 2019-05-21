package com.zsc.tbpractice.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.base.utils.StringUtils;
import com.zsc.general.entity.Admin;
import com.zsc.general.entity.User;
import com.zsc.general.entity.UserExample;
import com.zsc.general.entity.UserTeacher;
import com.zsc.tbpractice.dao.UserMapperExt;
import com.zsc.tbpractice.queryvo.BaseQuery;
import com.zsc.tbpractice.entityvo.UserTeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapperExt userMapperExt;

    public PageInfo<Admin> selectUserPageList(BaseQuery baseQueryVo) {
        PageHelper.startPage(baseQueryVo.getPageNo(), baseQueryVo.getPageSize());

        UserExample userExample = new UserExample();
        userExample.setOrderByClause("id  desc");
        UserExample.Criteria criteria = userExample.createCriteria();
        String name = baseQueryVo.getName();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andUserNameEqualTo(name);
        }
        List<User> userList = userMapperExt.selectByExample(userExample);
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }

    public UserTeacherVo selectUserTeacherByUserId(Integer userId) {
        return userMapperExt.selectUserTeacherByUserId(userId);
    }

    public User insertUserInfo(User user) {
        Integer integer = userMapperExt.insertUserInfo(user);
        //integer=1   user.getId()="主键值"
        Integer id = user.getId();
        System.out.println(integer);
        System.out.println(id);
        return user;
    }

    public Integer insertUserList(List<UserTeacher> userTeacherList) {
        Integer integer = userMapperExt.insertTeacherList(userTeacherList);
        return integer;
    }

    public  List<User> selectUserByIdList(List<Integer> userIdList) {
        List<User> users = userMapperExt.selectUserListByIdList(userIdList);
        return users;
    }


}