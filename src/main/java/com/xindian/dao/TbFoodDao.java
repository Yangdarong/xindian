package com.xindian.dao;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;
import com.xindian.pojo.TbUserFood;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface TbFoodDao {
    List<TbFood> queryAllFoodsInfo();

    List<TbFood> queryAllFoodsInfoFindPage(HashMap<String,Object> map);

    int countAllFoodsInfo(int mId);

    List<TbFoodType> queryAllTypes();

    void addFood(TbFood food);

    TbFood queryFoodById(int fid);

    void changeFoodPicture(@Param("fId") int fId, @Param("fUrl") String fUrl);

    void editFoodInfo(TbFood food);

    List<TbFood> queryFoodByFtId(@Param("ftId") int ftId);

    TbFood getFoodById(@Param("fId") int fId);

    List<TbFood> queryFoodsInfoByName(@Param("fName") String fName);

    TbUserFood queryUserFood(@Param("uId") int uId, @Param("fId") int fId);

    void createUserFood(@Param("uId") int uId, @Param("fId") int fId);
}
