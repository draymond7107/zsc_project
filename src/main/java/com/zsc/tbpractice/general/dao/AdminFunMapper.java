package com.zsc.tbpractice.general.dao;

import com.zsc.tbpractice.general.entity.AdminFun;
import com.zsc.tbpractice.general.entity.AdminFunExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminFunMapper {
    int countByExample(AdminFunExample example);

    int deleteByExample(AdminFunExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminFun record);

    int insertSelective(AdminFun record);

    List<AdminFun> selectByExample(AdminFunExample example);

    AdminFun selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminFun record, @Param("example") AdminFunExample example);

    int updateByExample(@Param("record") AdminFun record, @Param("example") AdminFunExample example);

    int updateByPrimaryKeySelective(AdminFun record);

    int updateByPrimaryKey(AdminFun record);
}