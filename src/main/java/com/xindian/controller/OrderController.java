package com.xindian.controller;

import com.xindian.pojo.TbOrder;
import com.xindian.service.TbOrderService;
import com.xindian.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.sql.Timestamp;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private TbOrderService service;

    /*-----------------------------安卓端-----------------------------------*/

    /**
     * 阶段1: 添加商品到购物车
     * @param request
     * @param response
     * @param order
     */
    @RequestMapping("/addBuyCar.json")
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
        } else {                                        // 订单创建
            //System.out.println("将新的菜添加到订单列表");
            // 2. 将食物添加到订单
            // 2.1 更新数据到实体
            order = service.queryCreatedOrder(order);
            // 3. 分析食物是否重复
            if (true) {                                 // 已经添加过这个菜品

            } else {                                    // 没有添加过这个菜品

            }
        }
    }

    /*-----------------------------管理端-----------------------------------*/
}
