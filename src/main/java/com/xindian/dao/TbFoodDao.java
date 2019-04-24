package com.xindian.dao;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;

import java.util.HashMap;
import java.util.List;

public interface TbFoodDao {
    List<TbFood> queryAllFoodsInfo();

    List<TbFood> queryAllFoodsInfoFindPage(HashMap<String,Object> map);

    int countAllFoodsInfo();

    List<TbFoodType> queryAllTypes();
}
