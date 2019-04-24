package com.xindian.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.MerLoginResultType;
import com.xindian.pojo.TbMer;
import com.xindian.service.TbMerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("/mer")
public class MerController {

    @Autowired
    private TbMerService service;

    @RequestMapping("/queryMer.do")
    public String queryMer(HttpServletRequest request, HttpServletResponse response) {

        String mLoginId = request.getParameter("mLoginId");
        String mPassword = request.getParameter("mPassword");

        TbMer mer = new TbMer();
        mer.setmLoginId(mLoginId);
        mer.setmPassword(mPassword);

        response.setContentType("application/x-www-form-urlencoded;charset=UTF-8");

        try {

            mer = service.queryMer(mer);

            if (mer != null) {  // 该用户存在
                HttpSession session = request.getSession();
//                session.setAttribute("mer", mer);
                addToSession(session, mer);
                if (mer.getmIsAdmin() == 1) {   // 超级管理员
                    return "redirect:/page/adminHome";
                } else {    // 普通商家用户
                    return "redirect:/page/merHome?mId=" + mer.getmId();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "login-fail";
    }

    @RequestMapping("/updateMer.do")
    public String updateMer(HttpServletRequest request, HttpServletResponse response) {
        String mId = request.getParameter("mId");
        String mName = request.getParameter("mName");
        String mAddress = request.getParameter("mAddress");
        String mPhone = request.getParameter("mPhone");
        String mIntro = request.getParameter("mIntro");

        TbMer mer = new TbMer();
        mer.setmId(Integer.parseInt(mId));
        mer.setmName(mName);
        mer.setmAddress(mAddress);
        mer.setmPhone(mPhone);
        mer.setmIntro(mIntro);
        response.setContentType("application/x-www-form-urlencoded;charset=UTF-8");

        service.updateMer(mer);
        mer = service.queryMerById(mer.getmId());    // 执行更改之后更新

        if (mer != null) {  // 该用户存在
            HttpSession session = request.getSession();
            addToSession(session, mer);
            return "redirect:/page/merInfo?mId=" + mer.getmId();
        }
        return "redirect:/loginFail";
    }

    private void addToSession(HttpSession session, TbMer mer) {
//                session.setAttribute("mer", mer);
        if (session.getAttribute("mer") == null) {  // 如果为空则添加
            session.setAttribute("mer", mer);
        } else {    // 替换session里面的用户对象
            session.removeAttribute("mer");
            session.setAttribute("mer", mer);   // 重新添加
        }
    }
}
