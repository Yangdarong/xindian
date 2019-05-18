package com.xindian.service;

import com.xindian.common.PageBean;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;

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

    /**
     * 商家更新订单Fo'o
     * @param oId
     * @param oState
     */
    void updateOrderState(int oId, int oState);

    /**
     * 商家获取订单的详细信息
     * @param oId
     * @return
     */
    List<TbOrderFood> queryOrderFoodsByOId(int oId);

    /**
     * 删除这条订单记录
     * @param ofId
     */
    void deleteOrderFoodByOfId(int ofId);

    /**
     * 根据页码返回订单记录
     * @param i
     * @param mId
     * @return
     */
    PageBean<TbOrder> queryAllOrderInfoFindPage(int i, int mId);
}
