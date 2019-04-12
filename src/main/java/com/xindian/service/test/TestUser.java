package com.xindian.service.test;

import com.xindian.pojo.TbUser;
import com.xindian.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.DriverManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestUser {

    @Autowired
    private TbUserService userService;

    @Test
    public void testConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://120.78.218.188:3306/db_xindian_test", "root", "Yxr960924!");

            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser() {

        TbUser user = new TbUser();
        user.setuLoginId("xiedc");
        user.setuPassword("xiedc");

        userService.addUser(user);

    }

    @Test
    public void testQueryUser() {
        TbUser user = new TbUser();
        user.setuLoginId("xiedc");
        user.setuPassword("xiedc");

        System.out.println(userService.queryUser(user));
    }

    @Test
    public void testUpdateUser() {
        TbUser user = new TbUser();

        user.setuId(1); // 修改yangxr
        user.setuSignature("yangxr");

        userService.updateUser(user);
    }

}
