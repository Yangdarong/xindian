package com.xindian.service.test;

import com.xindian.pojo.TbTitle;
import com.xindian.service.TbTitleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestTitle {

    @Autowired
    private TbTitleService titleService;

    @Test
    public void testQueryTitle() {
        List<TbTitle> titles = titleService.queryTitlesFromTab(1);

        System.out.println(titles.size());
    }
}
