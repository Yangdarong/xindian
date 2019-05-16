package com.xindian.dao;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodStrategy;
import com.xindian.pojo.TbStrategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbStrategyDao {
    void createNewStrategy(@Param("sState") int sState, @Param("uId") int uId);

    int queryIncreaseId();

    TbStrategy queryStrategyBySid(@Param("sId") int sId);

    void updateStrategyStateBySid(@Param("sId") int sId, @Param("sState") int sState);

    TbFoodStrategy queryExistFoodStrategy(@Param("sId") int sId, @Param("fId") int fId);

    void createNewFoodStrategy(@Param("sId") int sId, @Param("fId") int fId);

    List<TbFood> queryFoodStrategiesBySid(@Param("sId") int sId);

    void removeFoodStrategy(TbFoodStrategy foodStrategy);

    void updateStrategy(TbStrategy strategy);
}
