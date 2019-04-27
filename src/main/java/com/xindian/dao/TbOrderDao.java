package com.xindian.dao;

import com.xindian.pojo.TbOrder;
import org.apache.ibatis.annotations.Param;

public interface TbOrderDao {
    TbOrder queryCreatedOrder(TbOrder order);

    void createNewOrder(TbOrder order);

    void addFoodToOrder(@Param("oId") int oId, @Param("fId") int fId, @Param("ofAmount") int ofAmount);
}
