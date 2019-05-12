package com.xindian.utils;

import java.util.List;

public class ValueUtils {

    // 订单状态
    public static final int ORDER_ON_GOING = 1;
    public static final int ORDER_QUICK_SETTLE = 2;
    public static final int ORDER_USER_ACCEPT = 3;
    public static final int ORDER_USER_PAYOFF = 4;
    public static final int ORDER_MER_DETAILING = 5;
    public static final int ORDER_USER_CANCEL = 6;
    public static final int ORDER_FOOD_DELIVERY = 7;
    public static final int ORDER_COMPLETE = 8;
    public static final int ORDER_INVALID = 9;

    // 默认订单菜品数量 1
    public static final int FOOD_DEFAULT_AMOUNT = 1;

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNull(String s) {
        return s == null || s.equals("");
    }

    public static boolean isNull(List<Object> list) {
        return list.size() == 0;
    }
}
