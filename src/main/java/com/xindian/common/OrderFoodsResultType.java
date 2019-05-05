package com.xindian.common;

import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;

import java.util.List;

public class OrderFoodsResultType {
    private int state;
    private List<TbOrder> orders;
    private List<TbOrderFood> orderFoods;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<TbOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<TbOrder> orders) {
        this.orders = orders;
    }

    public List<TbOrderFood> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(List<TbOrderFood> orderFoods) {
        this.orderFoods = orderFoods;
    }
}
