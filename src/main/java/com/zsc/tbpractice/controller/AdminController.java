package com.zsc.tbpractice.controller;


import com.github.pagehelper.PageInfo;
import com.zsc.base.abs.BaseController;
import com.zsc.base.spring.JsonResult;
import com.zsc.general.entity.Admin;
import com.zsc.tbpractice.queryvo.BaseQuery;
import com.zsc.tbpractice.service.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员管理
 *
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */

@RestController
@RequestMapping("adm/admin")
public class AdminController extends BaseController {
    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AdminService adminService;

    @RequestMapping("/selectAdminPageList")
    public JsonResult selectAdminPageList(BaseQuery baseQueryVo) {

        PageInfo<Admin> pageInfo = adminService.selectPageList(baseQueryVo);
        return sendSuccess(pageInfo);
    }


}
