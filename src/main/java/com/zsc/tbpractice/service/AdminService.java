package com.zsc.tbpractice.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.base.utils.StringUtils;
import com.zsc.general.dao.AdminMapper;
import com.zsc.general.entity.Admin;
import com.zsc.general.entity.AdminExample;
import com.zsc.tbpractice.queryvo.BaseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;


    public PageInfo<Admin> selectPageList(BaseQuery baseQueryVo) {
        PageHelper.startPage(baseQueryVo.getPageNo(), baseQueryVo.getPageSize());

        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause("id asc");
        AdminExample.Criteria criteria = adminExample.createCriteria();
        String name = baseQueryVo.getName();
        if (StringUtils.isNotEmpty(name)) {
            //用户名
            criteria.andTrueNameEqualTo(name);
        }
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        PageInfo pageInfo = new PageInfo(adminList);
        return pageInfo;
    }
}
