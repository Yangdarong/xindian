package com.xindian.controller;

import com.xindian.common.PageBean;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;
import com.xindian.pojo.TbMer;
import com.xindian.service.TbFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private TbFoodService service;

    @RequestMapping("/qureyFoods.do")
    public String queryAllFoodsInfo(HttpServletRequest request) {
        PageBean<TbFood> pageBean = service.queryAllFoodsInfoFindPage(1);


        HttpSession session = request.getSession();
        if (pageBean != null) {
            addToSession(session, pageBean);
        }


        // 获取所有菜品种类
        List<TbFoodType> types = service.queryAllTypes();
        if (types != null && session.getAttribute("types") == null)
            session.setAttribute("types", types);
        return "redirect:/page/merFood";
    }

    private void addToSession(HttpSession session, PageBean<TbFood> pageBean) {
//                session.setAttribute("mer", mer);
        if (session.getAttribute("pageBean") == null) {  // 如果为空则添加
            session.setAttribute("pageBean", pageBean);
        } else {    // 替换session里面的用户对象
            session.removeAttribute("pageBean");
            session.setAttribute("pageBean", pageBean);   // 重新添加
        }
    }

}
