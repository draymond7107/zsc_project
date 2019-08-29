package com.zsc.tbpractice.general.dao;

import com.zsc.tbpractice.general.entity.Tclasses;
import com.zsc.tbpractice.general.entity.TclassesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TclassesMapper {
    int countByExample(TclassesExample example);

    int deleteByExample(TclassesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tclasses record);

    int insertSelective(Tclasses record);

    List<Tclasses> selectByExample(TclassesExample example);

    Tclasses selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tclasses record, @Param("example") TclassesExample example);

    int updateByExample(@Param("record") Tclasses record, @Param("example") TclassesExample example);

    int updateByPrimaryKeySelective(Tclasses record);

    int updateByPrimaryKey(Tclasses record);
}