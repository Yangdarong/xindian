package com.xindian.service;

import com.xindian.common.PageBean;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;
import com.xindian.pojo.TbUserFood;

import java.util.List;

public interface TbFoodService {

    // 查询所有菜(菜名+种类+商家+原价+折扣价)
    List<TbFood> queryAllFoodsInfo();
    // 返回分页数据
    PageBean<TbFood> queryAllFoodsInfoFindPage(int currentPage, int mId);
    // 分页返回商家自己的菜品
    //PageBean<TbFood> queryFoodsInfoFindPageByMerId(int currentPage, int merId);

    // 返回菜品种类
    List<TbFoodType> queryAllTypes();

    void addFood(TbFood food);

    TbFood queryFoodById(int fid);

    void changeFoodPicture(int fId, String fUrl);

    void editFoodInfo(TbFood food);

    List<TbFood> queryFoodByFtId(int ftId);

    TbFood getFoodById(int fId);

    /**
     * 根据名字返回相似的食物结果列表
     * @param fName
     * @return
     */
    List<TbFood> queryFoodsInfoByName(String fName);

    /**
     * 获取用户和食物的收藏记录
     * @param uId
     * @param fId
     * @return
     */
    TbUserFood queryUserFood(int uId, int fId);

    /**
     * 添加用户菜品的收藏记录
     * @param uId
     * @param fId
     */
    void createUserFood(int uId, int fId);
}
