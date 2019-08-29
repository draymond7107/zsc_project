package com.zsc.tbpractice.general.dao;

import com.zsc.tbpractice.general.entity.ClassTeacher;
import com.zsc.tbpractice.general.entity.ClassTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassTeacherMapper {
    int countByExample(ClassTeacherExample example);

    int deleteByExample(ClassTeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassTeacher record);

    int insertSelective(ClassTeacher record);

    List<ClassTeacher> selectByExample(ClassTeacherExample example);

    ClassTeacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassTeacher record, @Param("example") ClassTeacherExample example);

    int updateByExample(@Param("record") ClassTeacher record, @Param("example") ClassTeacherExample example);

    int updateByPrimaryKeySelective(ClassTeacher record);

    int updateByPrimaryKey(ClassTeacher record);
}