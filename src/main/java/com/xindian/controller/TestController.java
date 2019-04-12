package com.xindian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xindian.common.UserResultType;
import com.xindian.pojo.TbUser;
import com.xindian.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TbUserService userService;

    @RequestMapping("/queryUser.json")
    public void queryUserFromClient(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String uLoginId = request.getParameter("uLoginId");
        String uPassword = request.getParameter("uPassword");


        TbUser user = new TbUser();
        user.setuLoginId(uLoginId);
        user.setuPassword(uPassword);

        response.setContentType("application/json");
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();
        UserResultType result = new UserResultType();
        try {
            out = response.getWriter();
            user = userService.queryUser(user);


            if (user != null) {
                System.out.println("恭喜传输成功!");

                result.setState(1);
                result.setUser(user);

                out.write(mapper.writeValueAsString(result));

            } else {
                result.setState(0);
                result.setUser(null);
                out.write(mapper.writeValueAsString(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
