package com.zsc.general.dao;

import com.zsc.general.entity.Usertb;
import com.zsc.general.entity.UsertbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsertbMapper {
    int countByExample(UsertbExample example);

    int deleteByExample(UsertbExample example);

    int insert(Usertb record);

    int insertSelective(Usertb record);

    List<Usertb> selectByExample(UsertbExample example);

    int updateByExampleSelective(@Param("record") Usertb record, @Param("example") UsertbExample example);

    int updateByExample(@Param("record") Usertb record, @Param("example") UsertbExample example);
}