package com.zsc.tbpractice.general.dao;

import com.zsc.tbpractice.general.entity.RoleFun;
import com.zsc.tbpractice.general.entity.RoleFunExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleFunMapper {
    int countByExample(RoleFunExample example);

    int deleteByExample(RoleFunExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleFun record);

    int insertSelective(RoleFun record);

    List<RoleFun> selectByExample(RoleFunExample example);

    RoleFun selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleFun record, @Param("example") RoleFunExample example);

    int updateByExample(@Param("record") RoleFun record, @Param("example") RoleFunExample example);

    int updateByPrimaryKeySelective(RoleFun record);

    int updateByPrimaryKey(RoleFun record);
}