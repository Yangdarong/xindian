package com.xindian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.UserResultType;
import com.xindian.pojo.TbUser;
import com.xindian.service.TbUserService;
import com.xindian.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TbUserService userService;

    /*-----------------------------安卓端-----------------------------------*/
    @RequestMapping("/queryUser.json")
    public void queryUserFromClient(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String uLoginId = request.getParameter("uLoginId");
        String uPassword = request.getParameter("uPassword");


        TbUser user = new TbUser();
        user.setuLoginId(uLoginId);
        user.setuPassword(uPassword);
        user = userService.queryUser(user);
        if (user.getuUserStateId() == 0) {
            user.setuUserStateId(1);
            userService.updateLoginTime(user, new Timestamp(new Date().getTime()));
            userService.updateUserState(user);
        }

        UserResultType result = new UserResultType();
        UrlUtils.sendJsonData(response, result, user);

    }

    @RequestMapping("/addUser.json")
    public void addUserFromClient(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String uLoginId = request.getParameter("uLoginId");
        String uPassword = request.getParameter("uPassword");

        TbUser user = new TbUser();
        user.setuLoginId(uLoginId);

        response.setContentType("application/json");
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();
        UserResultType result = new UserResultType();

        try {
            out = response.getWriter();

            if (userService.queryUserByUserName(user) == null) {  // 用户没有注册
                user.setuPassword(uPassword);
                userService.addUser(user);
                result.setState(1);
                result.setUser(user);
            } else {    // 用户已经注册
                result.setState(0);
                result.setUser(null);
            }

            out.write(mapper.writeValueAsString(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
