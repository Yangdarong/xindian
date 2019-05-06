package com.xindian.dao;

import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbOrderDao {
    TbOrder queryCreatedOrder(TbOrder order);

    void createNewOrder(TbOrder order);

    void addFoodToOrder(@Param("oId") int oId, @Param("fId") int fId, @Param("ofAmount") int ofAmount);

    TbOrderFood queryOrderAndFood(@Param("oId") int oId, @Param("fId") int fId);

    List<TbOrder> queryBeingOrderByUId(@Param("uId") int uId, @Param("oState") int oState);

    List<TbOrderFood> queryFoodsByOrder(TbOrder order);

    void addOrderFoodAmount(TbOrderFood orderFood);

    void subOrderFoodAmount(TbOrderFood orderFood);
}
