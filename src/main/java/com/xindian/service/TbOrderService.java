package com.xindian.service;

import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;

import java.util.List;

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

    /**
     * 判断订单是否有已经添加过的食物
     * @param oId
     * @param fId
     * @return
     */
    TbOrderFood queryOrderAndFood(int oId, int fId);

    /**
     * 更新该订单食物数量+1
     * @param orderFood
     */
    void setOrderWithFoodAmount(TbOrderFood orderFood);

    /**
     * 该订单新增新的食物
     * @param oId
     * @param fId
     */
    void createNewOrderFood(int oId, int fId);

    List<TbOrder> queryBeingOrderByUId(int uId, int oState);

    List<TbOrderFood> queryFoodsByOrder(TbOrder order);
}
