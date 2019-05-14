package com.xindian.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.MerFoodsResultType;
import com.xindian.common.MerLoginResultType;
import com.xindian.common.PageBean;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import com.xindian.service.TbFoodService;
import com.xindian.service.TbMerService;
import com.xindian.utils.FileUtils;
import com.xindian.utils.UrlUtils;
import com.xindian.utils.ValueUtils;
import org.omg.PortableInterceptor.INACTIVE;
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
     * 查询所有订单信息(默认第一页)
     * @param request
     * @return
     */
    @RequestMapping("/queryOrders.do")
    public String queryAllOrdersInfo(HttpServletRequest request) {
        int mId = Integer.parseInt(request.getParameter("mId"));
        PageBean<TbOrder> orderPageBean = service.queryAllOrderInfoFindPage(1, mId);

        HttpSession session = request.getSession();
        if (orderPageBean != null) {
            addToSession(session, orderPageBean);
        }

        return "redirect:/page/orderInfo";
    }

    /**
     * 根据分页查找订单
     * @param request
     * @return
     */
    @RequestMapping("/queryPage.do")
    public String queryOrdersByPage(HttpServletRequest request) {
        int mId = Integer.parseInt(request.getParameter("mId"));
        int pId = Integer.parseInt(request.getParameter("pId"));

        PageBean<TbOrder> orderPageBean = service.queryAllOrderInfoFindPage(pId, mId);

        HttpSession session = request.getSession();
        if (orderPageBean != null) {
            addToSession(session, orderPageBean);
        }

        return "redirect:/page/merOrder";
    }

    /**
     * 删除一个订单的菜品
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cancelOrderFood.do")
    public String cancelOrderFood(HttpServletRequest request, HttpServletResponse response) {
        // 删除该项
        int ofId = Integer.parseInt(request.getParameter("ofId"));
        int oId = Integer.parseInt(request.getParameter("oId"));
        service.deleteOrderFoodByOfId(ofId);
        List<TbOrderFood> orderFoods = service.queryOrderFoodsByOId(oId);
        if (orderFoods == null) {
            service.updateOrderState(oId, ValueUtils.ORDER_USER_CANCEL);
        }
        // 刷新页面
        return "redirect:/page/editOrder";
    }

    /**
     * 前往订单详情页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toEditOrder.do")
    public String toEditOrder(HttpServletRequest request, HttpServletResponse response) {
        int oId = Integer.parseInt(request.getParameter("oId"));
        List<TbOrderFood> orderFoods = service.queryOrderFoodsByOId(oId);
        HttpSession session = request.getSession();

        if (session.getAttribute("orderFoods") == null) {  // 如果为空则添加
            session.setAttribute("orderFoods", orderFoods);
        } else {
            session.removeAttribute("orderFoods");
            session.setAttribute("orderFoods", orderFoods);
        }
        return "redirect:/page/editOrder";
    }

    /**
     * 通过订单ID　完成用户取消订单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cancelOrder.do")
    public String cancelOrder(HttpServletRequest request, HttpServletResponse response) {
        int oId = Integer.parseInt(request.getParameter("oId"));
        service.updateOrderState(oId, ValueUtils.ORDER_USER_CANCEL);

        return refreshMerPage(request);
    }

    private String refreshMerPage(HttpServletRequest request) {
        int mId = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("mer") != null) {  // 如果为空则添加
            TbMer mer = (TbMer) session.getAttribute("mer");
            mId = mer.getmId();
            return "redirect:/page/merHome?mId=" + mId;
        } else {    // 当没有Mid时显示登录失败
            return "redirect:/loginFail";
        }
    }

    @RequestMapping("/operatorOrder.do")
    public String operatorOrder(HttpServletRequest request, HttpServletResponse response) {
        int oId = Integer.parseInt(request.getParameter("oId"));
        int oState = Integer.parseInt(request.getParameter("oState"));
        service.updateOrderState(oId, oState + 1);
        // 获取商家ID
        return refreshMerPage(request);
    }

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

    private void addToSession(@NonNull HttpSession session, @NonNull PageBean<TbOrder> orderPageBean) {
//                session.setAttribute("mer", mer);
        if (session.getAttribute("orderPageBean") == null) {  // 如果为空则添加
            session.setAttribute("orderPageBean", orderPageBean);
        } else {    // 替换session里面的用户对象
            session.removeAttribute("orderPageBean");
            session.setAttribute("orderPageBean", orderPageBean);   // 重新添加
        }
    }
}
