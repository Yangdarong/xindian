package com.xindian.service;

import com.xindian.dao.TbOrderDao;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import com.xindian.pojo.TbOrderUser;
import org.apache.ibatis.annotations.Param;
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
    public void addOrderWithFoodAmount(TbOrderFood orderFood) {
        orderDao.addFoodAmount(orderFood);
    }

    @Override
    public void subOrderWithFoodAmount(TbOrderFood orderFood) {
        orderDao.subFoodAmount(orderFood);
    }

    @Override
    public List<TbOrder> queryBeingOrderByUId(int uId, int oState) {
        return orderDao.queryBeingOrderByUId(uId, oState);
    }

    @Override
    public List<TbOrderFood> queryFoodsByOrder(int oId) {
        return orderDao.queryFoodsByOrder(oId);
    }

    @Override
    public float countBuyCarTotal(int uId) {
        return orderDao.countTotal(uId);
    }

    @Override
    public void updateOrderState(TbOrder order) {
        orderDao.updateOrderState(order);
    }

    @Override
    public void updateOrderCost(TbOrder order) {
        orderDao.updateOrderCost(order);
    }

    @Override
    public void createAnOrderUser(TbOrderUser orderUser) {
        orderDao.createAnOrderUser(orderUser);
    }

    @Override
    public int queryCreatedOrderUserId() {
        return orderDao.queryCreatedOrderUserId();
    }

    @Override
    public void updateOrderWithUser(TbOrder order) {
        orderDao.updateOrderWithUser(order);
    }

    @Override
    public void updateOrderWithPhoneAndAddress(TbOrder order) {
        orderDao.updateOrderWithPhoneAndAddress(order);
    }

    @Override
    public int queryOrderUserByUId(int uId) {
        return orderDao.queryOrderUserByUId(uId);
    }

    @Override
    public List<TbOrder> queryOrderByOuId(int ouId) {
        return orderDao.queryOrderByOuId(ouId);
    }

    @Override
    public void deleteOrderFood(TbOrderFood orderFood) {
        orderDao.deleteOrderFood(orderFood);
    }

    @Override
    public int queryCreatedOid() {
        return orderDao.queryCreatedOid();
    }

    @Override
    public List<TbOrder> queryWaitConfirm(int oState, int uId) {
        return orderDao.queryWaitConfirm(oState, uId);
    }

}
