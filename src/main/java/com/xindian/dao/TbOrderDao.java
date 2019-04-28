package com.xindian.dao;

import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import org.apache.ibatis.annotations.Param;

public interface TbOrderDao {
    TbOrder queryCreatedOrder(TbOrder order);

    void createNewOrder(TbOrder order);

    void addFoodToOrder(@Param("oId") int oId, @Param("fId") int fId, @Param("ofAmount") int ofAmount);

    TbOrderFood queryOrderAndFood(@Param("oId") int oId, @Param("fId") int fId);
}
