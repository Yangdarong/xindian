package com.xindian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    // 登录成功
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }
    // 登录失败
    @RequestMapping("loginFail")
    public String loginFail() {
        return "login-fail";
    }

    // 用户主页
    @RequestMapping("/page/merHome")
    public String toMerHome() {
        return "mer-index";
    }

    // 管理员主页
    @RequestMapping("/page/adminHome")
    public String toAdminHome() {
        return "admin-index";
    }

    // 信息编辑
    @RequestMapping("/page/merInfo")
    public String editInfo() {
        return "user-info";
    }

    // 菜单管理
    @RequestMapping("page/merFood")
    public String editFoods() {
        return "food-edit";
    }


}
