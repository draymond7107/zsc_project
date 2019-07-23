package com.zsc.api.dingding;


import com.zsc.base.abs.BaseController;
import com.zsc.base.http.HttpResult;
import com.zsc.base.http.HttpUtils;
import com.zsc.base.spring.JsonResult;
import com.zsc.base.utils.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/4/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/dingding")
public class Login extends BaseController {
    @Autowired
    private TongXunLu tongXunLu;

    @RequestMapping("/login")
    public JsonResult login(String accessToken, String code) throws Exception {
        //先校验本地是否有数据

        //获得用户id
        String userId = tongXunLu.getUserIdByAccessTokenAndCode(accessToken, code);
        if (StringUtils.isEmpty(userId)) {
            logger.error("没有获得该用户id");
            return sendError("没有获得该用户id");
        }
        //获得用户详情
        tongXunLu.getUserByUserid(accessToken, userId);


        //校验用户

        //放到缓存或其他

        return null;
    }

}
