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
import java.io.PrintWriter;

@Controller
@RequestMapping("/mer")
public class MerController {

    @Autowired
    private TbMerService service;

    @RequestMapping("/queryMer.do")
    public void queryMer(HttpServletRequest request, HttpServletResponse response) {

        String mLoginId = request.getParameter("mLoginId");
        String mPassword = request.getParameter("mPassword");

        TbMer mer = new TbMer();
        mer.setmLoginId(mLoginId);
        mer.setmPassword(mPassword);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out;
        ObjectMapper mapper = new ObjectMapper();
        MerLoginResultType result = new MerLoginResultType();

        try {
            out = response.getWriter();
            mer = service.queryMer(mer);

            if (mer != null) {  // 该用户存在
                result.setState(1);
                result.setMer(mer);
            } else {
                result.setState(0);
                result.setMer(null);
            }
            out.write(mapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
