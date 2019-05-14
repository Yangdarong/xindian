package com.xindian.dao;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface TbMerDao {
    // 查询
    TbMer queryMer(TbMer mer);
    // 更新
    void updateMer(TbMer mer);
    //
    TbMer queryMerById(int mId);

    void changeMerPicture(TbMer mer);

    // 根据商家ID查找它的菜单
    List<TbFood> queryFoodsByMid(int mId);

    List<TbOrder> queryMerOrder(@Param("mId") int mId);

    void updateOrderState(@Param("oId") int oId, @Param("oState") int oState);

    List<TbOrderFood> queryOrderFoodsByOId(@Param("oId") int oId);

    void deleteOrderFoodByOfId(@Param("ofId") int ofId);

    int countAllOrderInfo(@Param("mId") int mId);

    List<TbOrder> queryAllOrdersInfoFindPage(HashMap<String, Object> map);
}
