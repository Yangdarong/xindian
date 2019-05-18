package com.xindian.service;

import com.xindian.dao.TbStrategyDao;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodStrategy;
import com.xindian.pojo.TbStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbStrategyServiceImpl implements TbStrategyService {

    @Autowired
    private TbStrategyDao strategyDao;

    @Override
    public void createNewStrategy(int sState, int uId) {
        strategyDao.createNewStrategy(sState, uId);
    }

    @Override
    public int queryIncreaseId() {
        return strategyDao.queryIncreaseId();
    }

    @Override
    public TbStrategy queryStrategyBySid(int sId) {
        return strategyDao.queryStrategyBySid(sId);
    }

    @Override
    public void updateStrategyStateBySid(int sId, int sState) {
        strategyDao.updateStrategyStateBySid(sId, sState);
    }

    @Override
    public TbFoodStrategy queryExistFoodStrategy(int sId, int fId) {
        return strategyDao.queryExistFoodStrategy(sId, fId);
    }

    @Override
    public void createNewFoodStrategy(int sId, int fId) {
        strategyDao.createNewFoodStrategy(sId, fId);
    }

    @Override
    public List<TbFood> queryFoodStrategiesBySid(int sId) {
        return strategyDao.queryFoodStrategiesBySid(sId);
    }

    @Override
    public void removeFoodStrategy(TbFoodStrategy foodStrategy) {
        strategyDao.removeFoodStrategy(foodStrategy);
    }

    @Override
    public void updateStrategy(TbStrategy strategy) {
        strategyDao.updateStrategy(strategy);
    }

    @Override
    public List<TbStrategy> queryUserFromStrategyDesc() {
        return strategyDao.queryUserFromStrategyDesc();
    }

    @Override
    public List<TbStrategy> queryStrategyOrderByCreateTime() {
        return strategyDao.queryStrategyOrderByCreateTime();
    }

    @Override
    public void createNewStrategyUser(int sId, int uId) {
        strategyDao.createNewStrategyUser(sId, uId);
    }

    @Override
    public List<TbStrategy> queryStrategyByUid(int uId) {
        return strategyDao.queryStrategyByUid(uId);
    }

    @Override
    public TbStrategy queryStrategyBySidAndUid(int sId, int uId) {
        return strategyDao.queryStrategyBySidAndUid(sId, uId);
    }
}
