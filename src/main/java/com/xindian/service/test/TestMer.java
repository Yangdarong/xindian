package com.xindian.service.test;

import com.xindian.pojo.TbMer;
import com.xindian.pojo.TbOrder;
import com.xindian.service.TbMerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestMer {

    @Autowired
    private TbMerService service;

    @Test
    public void testUpdateMer() {
        TbMer mer = new TbMer();
        mer.setmId(4);


        service.updateMer(mer);
    }

    @Test
    public void testMerOrder() {
        List list =
            service.queryMerOrder(4);

        System.out.println(list.size());
    }

    @Test
    public void testMerUpdate() {
        service.updateOrderState(13, 10);
    }
}
