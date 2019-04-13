package com.zsc.tbpractice.service;

import com.zsc.general.entity.Usertb;
import com.zsc.tbpractice.qo.BaseQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsertbServiceTest {

    @Autowired
    private UsertbService usertbService;

    @Test
    public void selectUsertbPageListTest() {
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setName("用");
        List list = usertbService.selectUsertbPageList(baseQuery);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    //问题：查询分页，开启了慢sql查询，统计的数量的sql被记录到slow.log，但是查询的列表sql没有被记录到慢sql
}