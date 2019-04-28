package com.xindian.service;

import com.xindian.dao.TbOrderDao;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Autowired
    private TbOrderDao orderDao;

    @Override
    public TbOrder queryCreatedOrder(TbOrder order) {
        return orderDao.queryCreatedOrder(order);
    }

    @Override
    public void createNewOrder(TbOrder order) {
        orderDao.createNewOrder(order);
    }

    @Override
    public void addFoodToOrder(int oId, int fId, int ofAmount) {
        orderDao.addFoodToOrder(oId, fId, ofAmount);
    }

    @Override
    public TbOrderFood queryOrderAndFood(int oId, int fId) {
        return orderDao.queryOrderAndFood(oId, fId);
    }

    @Override
    public void setOrderWithFoodAmount(TbOrderFood orderFood) {

    }

    @Override
    public void createNewOrderFood(int oId, int fId) {

    }
}
