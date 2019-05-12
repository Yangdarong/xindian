package com.xindian.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.CommonResultType;
import com.xindian.common.OrderFoodsResultType;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import com.xindian.pojo.TbOrderUser;
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
        //int fId = Integer.parseInt(request.getParameter("fId"));
        // 1.2 判断是否创建新的订单
        if (service.queryCreatedOrder(order) == null) { // 订单未创建 创建全新订单
            // 2. 创建订单 参数：uId, mId
            // 2.1. 操作:订单状态->进行中, 添加创建时间
            order.setoState(ValueUtils.ORDER_ON_GOING);
            createNewOrder(order, request, ValueUtils.FOOD_DEFAULT_AMOUNT);
        } else {                                        // 订单创建
            //System.out.println("将新的菜添加到订单列表");
            // 2. 将食物添加到订单
            // 2.1 更新数据到实体
            order = service.queryCreatedOrder(order);
            // 3. 分析食物是否重复
            TbOrderFood orderFood = service.queryOrderAndFood(order.getoId(), fId);
            if (orderFood != null) {                                 // 已经添加过这个菜品
                // 4.1 该订单食物数量+1
                service.addOrderWithFoodAmount(orderFood);
            } else {                                                 // 没有添加过这个菜品
                // 4.2 创建该订单食物
                service.addFoodToOrder(order.getoId(), fId, ValueUtils.FOOD_DEFAULT_AMOUNT);
            }
        }
    }

    private int fId;

    private void createNewOrder(TbOrder order, HttpServletRequest request, int ofAmount) {
        order.setoCreateTime(new Timestamp(new Date().getTime()));
        order.setoPayState(0);
        fId = Integer.parseInt(request.getParameter("fId"));
        service.createNewOrder(order);
        order = service.queryCreatedOrder(order);


        service.addFoodToOrder(order.getoId(), fId, ofAmount);
    }

    /**
     * 用户点击在菜品详情页直接结算
     * @param request
     */
    @RequestMapping("/doSettle.json")
    public void addFoodToSettle(HttpServletRequest request, HttpServletResponse response) {
        OrderFoodsResultType resultType = new OrderFoodsResultType();
        try {
            // 1、从request中获取json并转化成JavaBean对象
            String json = UrlUtils.getRequestJsonString(request);
            resultType = UrlUtils.jsonToBean(json, OrderFoodsResultType.class);
            // 2、判断是否存在订单列表
            if (resultType.getOrders() != null) {   // 存在订单列表
                // 3.1 提取订单列表
                List<TbOrder> orders = resultType.getOrders();
                // 3.2 更新每个order的状态
                float cost;
                float sum = 0;
                for (TbOrder order : orders) {
                    cost = 0;
                    // 3.3 更新订单金额
                    for (TbOrderFood orderFood : resultType.getOrderFoods()) {
                        if (order.getoId() == orderFood.getOrder().getoId()) {
//                            order.setoCost(orderFood.getOfAmount() * orderFood.getFood().getfDPrice());
//                            service.updateOrderCost(order);
                            cost += orderFood.getOfAmount() * orderFood.getFood().getfDPrice();
                        }
                        order.setoCost(cost);
                        sum += cost;
                    }

                }
                TbOrderUser orderUser = new TbOrderUser();
                orderUser.setOuCost(sum);
                orderUser.setOuPayTime(new Timestamp(new Date().getTime()));
                orderUser.setUser(orders.get(0).getUser());

                service.createAnOrderUser(orderUser);
                int ouId = service.queryCreatedOrderUserId();
                orderUser.setOuId(ouId);

                for (TbOrder order : orders) {
                    order.setOrderUser(orderUser);
                    order.setoState(ValueUtils.ORDER_USER_ACCEPT);  // 转化成为用户确定状态
                    service.updateOrderWithUser(order);
                }

            } else {                                    // 不存在订单列表
                // 3.1 创建新的订单列表
                List<TbOrderFood> orderFoods = resultType.getOrderFoods();
                TbOrder order = new TbOrder();
                order.setmId(orderFoods.get(0).getFood().getmId());
                order.setuId(orderFoods.get(0).getOrder().getuId());
                order.setoCreateTime(new Timestamp(new Date().getTime()));
                order.setoPayState(0);
                order.setoState(ValueUtils.ORDER_QUICK_SETTLE);
                service.createNewOrder(order);

                // 3.2 更新每个order的信息
                //order = service.queryCreatedOrder(order);
                int oId = service.queryCreatedOrderUserId();
                order.setoId(oId);
                float cost = 0;
                if (orderFoods.get(0).getOfAmount() == 0) {
                    cost = 1 * orderFoods.get(0).getFood().getfDPrice();
                } else {
                    cost = orderFoods.get(0).getOfAmount() * orderFoods.get(0).getFood().getfDPrice();
                }

                order.setoCost(cost);
                service.updateOrderCost(order);

                // 3.3 汇总这些订单形成一笔交易
                service.addFoodToOrder(order.getoId(), orderFoods.get(0).getFood().getfId(), orderFoods.get(0).getOfAmount());

                TbOrderUser orderUser = new TbOrderUser();
                orderUser.setOuCost(order.getoCost());
                orderUser.setOuPayTime(new Timestamp(new Date().getTime()));
                orderUser.setuId(order.getuId());

                service.createAnOrderUser(orderUser);
                int ouId = service.queryCreatedOrderUserId();
                order.setOuId(ouId);
                order.setoState(ValueUtils.ORDER_USER_ACCEPT);  // 转化成为用户确定状态
                service.updateOrderWithUser(order);

                // 支付成功
            }


            UrlUtils.sendJsonData(response, 1, "成功了");
        } catch (Exception e) {
            e.printStackTrace();
            UrlUtils.sendJsonData(response, 0, "失败了");
        }

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
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (orders.size() != 0) {    // 当前存在进行中的订单，
            // 返回订单信息
            List<TbOrderFood> orderFoods = new ArrayList<>();
            for (TbOrder order : orders) {
                orderFoods.addAll(service.queryFoodsByOrder(order));
            }
            // 返回所属的菜单信息

            try {
                out = response.getWriter();
                if (orderFoods.size() != 0) {
                    result.setState(1);
                    result.setOrders(orders);
                    result.setOrderFoods(orderFoods);
                    result.setMessage("查询购物车成功");
                }
                out.write(mapper.writeValueAsString(result));
            } catch (Exception e) {
                e.printStackTrace();
                result.setState(0);
                result.setOrders(null);
                result.setOrderFoods(null);
                result.setMessage("没有购物车记录");
            }

        } else {
            // 直接返回没有内容
            result.setState(0);
            result.setOrders(null);
            result.setOrderFoods(null);
            result.setMessage("没有购物车记录");
        }

    }

    @RequestMapping("/foodNumAdd.json")
    public void addOrderFoodAmount(HttpServletResponse response, TbOrderFood orderFood) {
        service.addOrderWithFoodAmount(orderFood);
        UrlUtils.sendJsonData(response, 1, "SUCCESS");
    }

    @RequestMapping("/foodNumSub.json")
    public void subOrderFoodAmount(HttpServletResponse response, TbOrderFood orderFood) {
        if (orderFood.getOfAmount() > 1) {
            service.subOrderWithFoodAmount(orderFood);
            UrlUtils.sendJsonData(response, 1, "SUCCESS");
        }
    }

    @RequestMapping("/total.json")
    public void countMoney(HttpServletResponse response, TbUser user) {
        float total = service.countBuyCarTotal(user.getuId());
        UrlUtils.sendJsonData(response, 1, String.format("%.2f", total));
    }

    /*-----------------------------管理端-----------------------------------*/
}
