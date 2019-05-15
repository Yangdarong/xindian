package com.xindian.service;

import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import com.xindian.pojo.TbOrderUser;

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
    void addOrderWithFoodAmount(TbOrderFood orderFood);

    void subOrderWithFoodAmount(TbOrderFood orderFood);


    List<TbOrder> queryBeingOrderByUId(int uId, int oState);

    List<TbOrderFood> queryFoodsByOrder(TbOrder order);

    /**
     * 计算当前订单的总金额
     * @param uId
     * @return
     */
    float countBuyCarTotal(int uId);

    /**
     * 更新订单状态
     * @param order
     */
    void updateOrderState(TbOrder order);

    /**
     * 更新订单金额
     * @param order
     */
    void updateOrderCost(TbOrder order);

    /**
     * 创建一笔交易的信息
     * @param orderUser
     */
    void createAnOrderUser(TbOrderUser orderUser);

    /**
     * 获取最近一次插入的ID
     * @return
     */
    int queryCreatedOrderUserId();

    /**
     * 更新订单进入用户交易表
     * @param order
     */
    void updateOrderWithUser(TbOrder order);

    /**
     * 更新订单的手机信息和收货地址信息
     * @param order
     */
    void updateOrderWithPhoneAndAddress(TbOrder order);

    /**
     * 通过用户ID获取最新创建的交易记录ID
     * @param uId
     * @return
     */
    int queryOrderUserByUId(int uId);

    /**
     * 通过交易记录ID获取对应的订单列表结果
     * @param ouId
     * @return
     */
    List<TbOrder> queryOrderByOuId(int ouId);

    /**
     * 删除订单食物数据
     * @param orderFood
     */
    void deleteOrderFood(TbOrderFood orderFood);


}
