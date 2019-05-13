package com.xindian.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.MerFoodsResultType;
import com.xindian.common.MerLoginResultType;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;
import com.xindian.pojo.TbOrder;
import com.xindian.service.TbFoodService;
import com.xindian.service.TbMerService;
import com.xindian.utils.FileUtils;
import com.xindian.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/mer")
public class MerController {

    @Autowired
    private TbMerService service;

    /*-----------------------------安卓端-----------------------------------*/
    @RequestMapping("/queryMerAndFoods.json")
    public void queryMerAndFoods(HttpServletRequest request, HttpServletResponse response, TbMer mer) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        mer = service.queryMerById(mer.getmId());
        List<TbFood> foods = service.queryFoodsByMid(mer.getmId());
        MerFoodsResultType result = new MerFoodsResultType();
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            out = response.getWriter();
            if (mer != null) {
                result.setState(1);
                result.setMer(mer);
                result.setFoods(foods);
            } else {
                result.setState(0);
                result.setMer(null);
                result.setFoods(null);
            }
            out.write(mapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*-----------------------------管理端-----------------------------------*/
    /**
     * 后台系统登录
     * @param request
     * @param response
     * @return
     */
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
                addToSession(session, mer);

                // 查询订单信息
                List<TbOrder> orders = service.queryMerOrder(mer.getmId());
                if (session.getAttribute("orders") == null) {  // 如果为空则添加
                    session.setAttribute("orders", orders);
                } else {    // 替换session里面的用户对象
                    session.removeAttribute("orders");
                    session.setAttribute("orders", orders);   // 重新添加
                }

                // 查询任务

                // 查询最新评论

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

    /**
     * 修改商店的头像
     * @param mer
     * @param request
     * @param pictureFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/editMerPic")
    public String changeMerPicture(TbMer mer,HttpServletRequest request, MultipartFile pictureFile) throws IOException {
        String mUrl = FileUtils.uploadPicture(pictureFile, request, UrlUtils.TYPE_MER);
        mer.setmUrl(mUrl);
        service.changeMerPicture(mer);
        mer = service.queryMerById(mer.getmId());
        addToSession(request.getSession(), mer);

        return "redirect:/page/merInfo";
    }

    /**
     * 后台系统用户修改信息
     * @param request
     * @param response
     * @return
     */
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

    private void addToSession(@NonNull HttpSession session, @NonNull TbMer mer) {
//                session.setAttribute("mer", mer);
        if (session.getAttribute("mer") == null) {  // 如果为空则添加
            session.setAttribute("mer", mer);
        } else {    // 替换session里面的用户对象
            session.removeAttribute("mer");
            session.setAttribute("mer", mer);   // 重新添加
        }
    }
}
