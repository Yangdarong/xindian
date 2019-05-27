package com.xindian.service;

import com.xindian.common.PageBean;
import com.xindian.dao.TbMerDao;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;
import com.xindian.pojo.TbOrder;
import com.xindian.pojo.TbOrderFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TbMerServiceImpl implements TbMerService {

    @Autowired
    private TbMerDao merDao;

    @Override
    public TbMer queryMer(TbMer mer) {
        return merDao.queryMer(mer);
    }

    @Override
    public TbMer queryMerById(int mId) {
        return merDao.queryMerById(mId);
    }

    @Override
    public void updateMer(TbMer mer) {
        merDao.updateMer(mer);
    }

    @Override
    public void changeMerPicture(TbMer mer) {
        merDao.changeMerPicture(mer);
    }

    @Override
    public List<TbFood> queryFoodsByMid(int mId) {
        return merDao.queryFoodsByMid(mId);
    }

    @Override
    public List<TbOrder> queryMerOrder(int mId) {
        return merDao.queryMerOrder(mId);
    }

    @Override
    public void updateOrderState(int oId, int oState) {
        merDao.updateOrderState(oId, oState);
    }

    @Override
    public List<TbOrderFood> queryOrderFoodsByOId(int oId) {
        return merDao.queryOrderFoodsByOId(oId);
    }

    @Override
    public void deleteOrderFoodByOfId(int ofId) {
        merDao.deleteOrderFoodByOfId(ofId);
    }

    @Override
    public PageBean<TbOrder> queryAllOrderInfoFindPage(int currentPage, int mId) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<TbOrder> orderPageBean = new PageBean<>();

        // 封装当前页面
        orderPageBean.setCurrentPage(currentPage);
        int pageSize = 10
                ;
        orderPageBean.setPageSize(pageSize);

        // 封装总记录数
        int totalCount = merDao.countAllOrderInfo(mId);
        orderPageBean.setTotalCount(totalCount);

        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);    // 向上取整
        orderPageBean.setTotalPage(num.intValue());

        map.put("start", (currentPage - 1) * pageSize);
        map.put("size", orderPageBean.getPageSize());
        map.put("mId", mId);

        // 封装每页显示的数据
        List<TbOrder> orders = merDao.queryAllOrdersInfoFindPage(map);
        orderPageBean.setLists(orders);

        return orderPageBean;
    }
}
