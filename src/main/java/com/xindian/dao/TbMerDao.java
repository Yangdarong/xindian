package com.xindian.dao;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;

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
}
