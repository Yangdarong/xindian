package com.xindian.dao;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import com.xindian.pojo.TbOrderUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbOrderDao {
    TbOrder queryCreatedOrder(TbOrder order);

    void createNewOrder(TbOrder order);

    void addFoodToOrder(@Param("oId") int oId, @Param("fId") int fId, @Param("ofAmount") int ofAmount);

    TbOrderFood queryOrderAndFood(@Param("oId") int oId, @Param("fId") int fId);

    List<TbOrder> queryBeingOrderByUId(@Param("uId") int uId, @Param("oState") int oState);

    List<TbOrderFood> queryFoodsByOrder(@Param("oId") int oId);

    void addFoodAmount(TbOrderFood orderFood);

    void subFoodAmount(TbOrderFood orderFood);

    float countTotal(@Param("uId") int uId);

    void updateOrderState(TbOrder order);

    void updateOrderCost(TbOrder order);

    void createAnOrderUser(TbOrderUser orderUser);

    int queryCreatedOrderUserId();

    void updateOrderWithUser(TbOrder order);

    void updateOrderWithPhoneAndAddress(TbOrder order);

    int queryOrderUserByUId(@Param("uId") int uId);

    List<TbOrder> queryOrderByOuId(@Param("ouId") int ouId);

    void deleteOrderFood(TbOrderFood orderFood);

    int queryCreatedOid();

    List<TbOrder> queryWaitConfirm(@Param("oState") int oState, @Param("uId") int uId);
}
