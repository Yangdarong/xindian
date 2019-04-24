package com.xindian.service;

import com.xindian.common.PageBean;
import com.xindian.dao.TbFoodDao;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TbFoodServiceImpl implements TbFoodService {

    @Autowired
    private TbFoodDao foodDao;

    @Override
    public List<TbFood> queryAllFoodsInfo() {
        return foodDao.queryAllFoodsInfo();
    }

    @Override
    public PageBean<TbFood> queryAllFoodsInfoFindPage(int currentPage) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<TbFood> pageBean = new PageBean<>();

        // 封装当前页面
        pageBean.setCurrentPage(currentPage);
        int pageSize = 5;
        pageBean.setPageSize(pageSize);

        // 封装总记录数
        int totalCount = foodDao.countAllFoodsInfo();
        pageBean.setTotalCount(totalCount);

        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);    // 向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start", (currentPage - 1) * pageSize);
        map.put("size", pageBean.getPageSize());
        // 封装每页显示的数据
        List<TbFood> foods = foodDao.queryAllFoodsInfoFindPage(map);
        pageBean.setLists(foods);

        return pageBean;
    }

    @Override
    public List<TbFoodType> queryAllTypes() {
        return foodDao.queryAllTypes();
    }
}
