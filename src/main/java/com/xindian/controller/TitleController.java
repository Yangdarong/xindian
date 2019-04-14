package com.xindian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.TitleResultType;
import com.xindian.pojo.TbTitle;
import com.xindian.service.TbTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/title")
public class TitleController {


    @Autowired
    private TbTitleService service;

    @RequestMapping("/queryTitles.json")
    public void queryTitles(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String tabSelect = request.getParameter("tFrom");
        int tFrom = Integer.parseInt(tabSelect);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();
        TitleResultType result = new TitleResultType();
        List<TbTitle> titles;
        try {
            out = response.getWriter();
            titles = service.queryTitlesFromTab(tFrom);

            if (titles.size() != 0) {
                System.out.println("恭喜传输成功!");

                result.setState(1);
                result.setTitles(titles);

                out.write(mapper.writeValueAsString(result));

            } else {
                result.setState(0);
                result.setTitles(null);
                out.write(mapper.writeValueAsString(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
