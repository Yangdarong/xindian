package com.xindian.service;

import com.xindian.common.PageBean;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;

import java.util.List;

public interface TbFoodService {

    // 查询所有菜(菜名+种类+商家+原价+折扣价)
    List<TbFood> queryAllFoodsInfo();
    // 返回分页数据
    PageBean<TbFood> queryAllFoodsInfoFindPage(int currentPage);

    // 返回菜品种类
    List<TbFoodType> queryAllTypes();
}
