package com.xindian.service.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.pojo.TbUser;
import org.junit.Test;

/**
 * 测试 Jackson 的使用
 *
 */
public class TestJackson {

    @Test
    public void test1() throws JsonProcessingException {
        TbUser user = new TbUser();

        user.setuLoginId("xietao");
        user.setuPassword("xietao");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        System.out.println(json);
    }
}
