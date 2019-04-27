package com.xindian.service;

import com.xindian.pojo.TbOrder;

public interface TbOrderService {

    /**
     * 查询订单是否有过创建
     * @param order
     * @return
     */
    TbOrder queryCreatedOrder(TbOrder order);

    /**
     * 新建一个全新的订单
     * @param order
     */
    void createNewOrder(TbOrder order);

    /**
     * 订单加入一个新的食物
     * @param oId
     * @param fId
     * @param ofAmount
     */
    void addFoodToOrder(int oId, int fId, int ofAmount);
}
