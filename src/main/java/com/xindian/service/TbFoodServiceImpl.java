package com.xindian.service;

import com.xindian.common.PageBean;
import com.xindian.dao.TbFoodDao;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;
import com.xindian.pojo.TbUserFood;
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
        return null;
    }

    @Override
    public PageBean<TbFood> queryAllFoodsInfoFindPage(int currentPage, int mId) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<TbFood> pageBean = new PageBean<>();

        // 封装当前页面
        pageBean.setCurrentPage(currentPage);
        int pageSize = 5;
        pageBean.setPageSize(pageSize);

        // 封装总记录数
        int totalCount = foodDao.countAllFoodsInfo(mId);
        pageBean.setTotalCount(totalCount);

        // 封装总页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);    // 向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start", (currentPage - 1) * pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("mId", mId);
        // 封装每页显示的数据
        List<TbFood> foods = foodDao.queryAllFoodsInfoFindPage(map);
        pageBean.setLists(foods);

        return pageBean;
    }

    @Override
    public List<TbFoodType> queryAllTypes() {
        return foodDao.queryAllTypes();
    }

    @Override
    public void addFood(TbFood food) {
        foodDao.addFood(food);
    }

    @Override
    public TbFood queryFoodById(int fid) {
        return foodDao.queryFoodById(fid);
    }

    @Override
    public void changeFoodPicture(int fId, String fUrl) {
        foodDao.changeFoodPicture(fId, fUrl);
    }

    @Override
    public void editFoodInfo(TbFood food) {
        foodDao.editFoodInfo(food);
    }


    @Override
    public List<TbFood> queryFoodByFtId(int ftId) {
        return foodDao.queryFoodByFtId(ftId);
    }

    @Override
    public TbFood getFoodById(int fId) {
        return foodDao.getFoodById(fId);
    }

    @Override
    public List<TbFood> queryFoodsInfoByName(String fName) {
        return foodDao.queryFoodsInfoByName(fName);
    }

    @Override
    public TbUserFood queryUserFood(int uId, int fId) {
        return foodDao.queryUserFood(uId, fId);
    }

    @Override
    public void createUserFood(int uId, int fId) {
        foodDao.createUserFood(uId, fId);
    }
}
