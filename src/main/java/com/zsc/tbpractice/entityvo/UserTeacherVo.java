package com.zsc.tbpractice.entityvo;

import lombok.Data;

import java.util.List;

/**
 * 查询用户的老师
 * 一个学生有多位老师
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */
@Data
public class UserTeacherVo  {
    //用户信息
    private Integer id;

    private String userName;

    private String trueName;

    private String password;

    private String sex;

    private Integer age;

    private String photo;

    private  ClassesVo classesVo ;

    //老师信息
    private List<TeacherVo> teacherList ;

}