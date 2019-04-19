package com.zsc.tbpractice.http.dingding;

import com.zsc.base.spring.JsonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ThirdPartAppTest {
    Log log = LogFactory.getLog(ThirdPartAppTest.class);


    @Autowired
    private ThirdPartApp thirdPartApp;
    @Autowired
    private TongXunLu tongXunLu;

    @Test
    public void get_corp_token() {

        try {

            String accessToken = thirdPartApp.get_corp_token("ding57c02b5dcf8ed7ce35c2f4657eb6378f");


            Map<String, Object> user = tongXunLu.getUserByUserid(accessToken, "1555331032342-151744383");
            //     JsonResult list = tongXunLu.getDeptMemberUserIdListByDeptId(accessToken, "111656661");
        //    JsonResult list1 = tongXunLu.getDepartmentIdListByParentId(accessToken, "1");
            //  (String accessToken, String departmentId, Long offset, Integer size, String order) throws Exception {
      //      JsonResult list1 = tongXunLu.getDepartmentInfoBydepartmentid(accessToken, "111457582",20L,1,"entry_asc");


            log.error(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}