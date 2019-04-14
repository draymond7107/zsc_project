package com.zsc.tbpractice.DingDing;


import com.zsc.tbpractice.http.dingding.DingDing;
import com.zsc.tbpractice.http.dingding.DingDingUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DingDingUserTest {

    @Autowired
    private DingDing dingDing;

    @Test
    public void getAccessTokenTest() {

        try {
            dingDing.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getDingDingSign() {

        try {

            DingDingUtils.getDingDingSign("testappSecret");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}