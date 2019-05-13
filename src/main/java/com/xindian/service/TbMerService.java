package com.xindian.service;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;
import com.xindian.pojo.TbOrder;

import java.util.List;

public interface TbMerService {

    TbMer queryMer(TbMer mer);

    TbMer queryMerById(int mId);

    void updateMer(TbMer mer);

    void changeMerPicture(TbMer mer);

    List<TbFood> queryFoodsByMid(int mId);

    /**
     * 通过商家ID获取Order列表
     * @param mId
     * @return
     */
    List<TbOrder> queryMerOrder(int mId);
}
