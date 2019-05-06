package com.xindian.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.OrderFoodsResultType;
import com.xindian.pojo.TbFoodType;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import com.xindian.pojo.TbUser;
import com.xindian.service.TbOrderService;
import com.xindian.utils.UrlUtils;
import com.xindian.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private TbOrderService service;

    private String message;
    private int state = 0;

    /*-----------------------------安卓端-----------------------------------*/

    /**
     * 阶段1: 添加商品到购物车
     * @param request
     * @param response
     * @param order
     */
    @RequestMapping("/addBuyCar.do")
    public void addFoodToOrder(HttpServletRequest request, HttpServletResponse response, TbOrder order) {
        // 1 判断是否有正在进行的订单(对商家) :
        // 1.1 接收点单食物ID
        int fId = Integer.parseInt(request.getParameter("fId"));
        // 1.2 判断是否创建新的订单
        if (service.queryCreatedOrder(order) == null) { // 订单未创建 创建全新订单
            // 2. 创建订单 参数：uId, mId
            // 2.1. 操作:订单状态->进行中, 添加创建时间
            order.setoState(ValueUtils.ORDER_ON_GOING);
            order.setoCreateTime(new Timestamp(new Date().getTime()));
            service.createNewOrder(order);
            // 2.2 更新数据到实体
            order = service.queryCreatedOrder(order);

            // 3. 添加食物ID进入订单
            service.addFoodToOrder(order.getoId(), fId, ValueUtils.FOOD_DEFAULT_AMOUNT);
            state = 1;
            message = "创建新订单";
        } else {                                        // 订单创建
            //System.out.println("将新的菜添加到订单列表");
            // 2. 将食物添加到订单
            // 2.1 更新数据到实体
            order = service.queryCreatedOrder(order);
            // 3. 分析食物是否重复
            TbOrderFood orderFood = service.queryOrderAndFood(order.getoId(), fId);
            if (orderFood != null) {                                 // 已经添加过这个菜品
                // 4.1 该订单食物数量+1
                service.setOrderWithFoodAmount(orderFood);
                state = 2;
                message = "更新菜品数量";
            } else {                                                 // 没有添加过这个菜品
                // 4.2 创建该订单食物
                service.createNewOrderFood(order.getoId(), fId);
                state = 3;
                message = "添加新菜品";
            }
        }

        // 返回 Common JSON 数据
        UrlUtils.sendJsonData(response, state, message);
    }

    /**
     * 获取用户的订单,规定用户的订单至多存在一个是进行中的状态
     *
     * @param response
     * @param request
     * @param user
     */
    @RequestMapping("/queryBayCar.json")
    public void queryOrderWithFoods(HttpServletResponse response, HttpServletRequest request, TbUser user) {
        // 1、 通过用户信息查询订单表
        List<TbOrder> orders = service.queryBeingOrderByUId(user.getuId(), 1);
        OrderFoodsResultType result = new OrderFoodsResultType();
        if (orders.size() != 0) {    // 当前存在进行中的订单，
            // 返回订单信息
            List<TbOrderFood> orderFoods = new ArrayList<>();
            for (TbOrder order : orders) {
                orderFoods.addAll(service.queryFoodsByOrder(order));
            }
            // 返回所属的菜单信息

            PrintWriter out = null;
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            try {
                out = response.getWriter();

                    result.setState(1);
                    result.setOrders(orders);
                    result.setOrderFoods(orderFoods);

                out.write(mapper.writeValueAsString(result));
            } catch (Exception e) {
                e.printStackTrace();
                result.setState(0);
                result.setOrders(null);
                result.setOrderFoods(null);
            }

        } else {
            // 直接返回没有内容
            result.setState(0);
            result.setOrders(null);
            result.setOrderFoods(null);
        }

    }

    /**
     * 菜品数量加一
     * @param request
     * @param response
     * @param orderFood
     */
    @RequestMapping("/addFoodAmount.json")
    public void addOrderFoodAmount(HttpServletRequest request, HttpServletResponse response, TbOrderFood orderFood) {
        state = 1;
        message = "数目加1";
        service.setOrderWithFoodAmount(orderFood);
        UrlUtils.sendJsonData(response, state, message);
    }

    /**
     * 菜品数量减一
     * @param response
     * @param orderFood
     */
    @RequestMapping("/subFoodAmount.json")
    public void subOrderFoodAmount(HttpServletResponse response, TbOrderFood orderFood) {
        state = 1;
        message = "数目减1";
        service.subOrderWithFoodAmount(orderFood);
        UrlUtils.sendJsonData(response, state, message);
    }

    /**
     * 删除购物车中的菜品订单
     * @param response
     * @param orderFood
     */
    @RequestMapping("deleteFoodInOrder.json")
    public void deleteOrderFood(HttpServletResponse response, TbOrderFood orderFood) {
        // 1. 从订单-食物表中删除这条记录
        service.deleteOrderFood(orderFood);

        // 2. 通过订单ID查找订单食物表中关于该订单的记录数
        int count = service.queryFoodsCountByOId(orderFood);

        if (count == 0) {   // 如果该订单没有食物列表
            // 删除订单
            service.deleteOrder(orderFood);
        }
        state = 1;
        message = "删除菜品成功";
        UrlUtils.sendJsonData(response, state, message);
    }

    @RequestMapping("deleteAllOrderFoods.json")
    public void deleteOrders(HttpServletResponse response, TbOrderFood orderFood) {
        // 1. 删除订单食物表中所有该订单ID的所有记录
        service.deleteOrderFoods(orderFood);
        // 2. 从订单表中删除这项订单
        service.deleteOrder(orderFood);

        state = 1;
        message = "清空成功";
        UrlUtils.sendJsonData(response, state, message);
    }

    /*-----------------------------管理端-----------------------------------*/
}
