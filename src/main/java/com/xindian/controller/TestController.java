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

    public void addUserFromClient(HttpServletRequest request, HttpServletResponse response) {

    }

}
