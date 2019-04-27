package com.xindian.service.test;

import com.xindian.common.PageBean;
import com.xindian.pojo.TbFood;
import com.xindian.service.TbFoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestFood {

    @Autowired
    private TbFoodService service;

    @Test
    public void testQueryFoodsInfo() {
        List<TbFood> foods = service.queryAllFoodsInfo();
        System.out.println(foods.size());
    }

    @Test
    public void testQueryFoodById() {    // 实现分页
        TbFood food = service.queryFoodById(21);
        System.out.println(food.getfName());
    }
}


