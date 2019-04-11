package com.xindian.controller;

import com.xindian.pojo.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/test")
public class TestController {

    public void queryUserFromClient(HttpServletRequest request, HttpServletResponse response,
                                    Model model, @RequestParam("id") int id) {

        TbUser user = new TbUser();
        user.setuId(id);

        response.setContentType("application/json");
        PrintWriter out = null;


    }
}
