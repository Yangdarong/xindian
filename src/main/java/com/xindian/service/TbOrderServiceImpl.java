package com.xindian.service;

import com.xindian.dao.TbOrderDao;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        orderDao.addOrderFoodAmount(orderFood);
    }

    @Override
    public void subOrderWithFoodAmount(TbOrderFood orderFood) {
        orderDao.subOrderFoodAmount(orderFood);
    }

    @Override
    public void createNewOrderFood(int oId, int fId) {

    }

    @Override
    public List<TbOrder> queryBeingOrderByUId(int uId, int oState) {
        return orderDao.queryBeingOrderByUId(uId, oState);
    }

    @Override
    public List<TbOrderFood> queryFoodsByOrder(TbOrder order) {
        return orderDao.queryFoodsByOrder(order);
    }

    @Override
    public void deleteOrder(TbOrderFood orderFood) {

    }

    @Override
    public int queryFoodsCountByOId(TbOrderFood orderFood) {
        return 0;
    }

    @Override
    public void deleteOrderFood(TbOrderFood orderFood) {

    }

    @Override
    public void deleteOrderFoods(TbOrderFood orderFood) {

    }

}
