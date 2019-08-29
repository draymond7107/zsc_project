package com.zsc.tbpractice.service;


import com.github.pagehelper.PageHelper;
import com.zsc.base.utils.StringUtils;
import com.zsc.tbpractice.general.dao.UsertbMapper;
import com.zsc.tbpractice.general.entity.UsertbExample;
import com.zsc.tbpractice.queryvo.BaseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/4/13
 * @since 1.0.0
 */
@Service
public class UsertbService {

    @Autowired
    private UsertbMapper usertbMapper;


    public List selectUsertbPageList(BaseQuery baseQueryVo) {
        PageHelper.startPage(baseQueryVo.getPageNo(), baseQueryVo.getPageSize());

        UsertbExample usertbExample = new UsertbExample();
        String name = baseQueryVo.getName();

        if (StringUtils.isNotEmpty(name)) {
            usertbExample.createCriteria().andUnameLike(name+"%");
        }
        return usertbMapper.selectByExample(usertbExample);

    }
}
