package com.xindian.service;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodStrategy;
import com.xindian.pojo.TbStrategy;

import java.util.List;

public interface TbStrategyService {
    /**
     * 通过用户ID创建美食攻略,初始化编辑状态
     * @param sState
     * @param uId
     */
    void createNewStrategy(int sState, int uId);

    /**
     * 获取新增的主键值
     * @return
     */
    int queryIncreaseId();

    /**
     * 通过ID主键查找美食攻略
     * @param sId
     * @return
     */
    TbStrategy queryStrategyBySid(int sId);

    /**
     * 更新美食攻略状态
     * @param sId
     * @param sState
     */
    void updateStrategyStateBySid(int sId, int sState);

    /**
     * 通过两个关联主键查找美食攻略是否被添加
     * @param sId
     * @param fId
     * @return
     */
    TbFoodStrategy queryExistFoodStrategy(int sId, int fId);

    /**
     * 添加新的美食攻略
     * @param sId
     * @param fId
     */
    void createNewFoodStrategy(int sId, int fId);

    /**
     * 通过 sId 来获取攻略中的食物列表
     * @param sId
     * @return
     */
    List<TbFood> queryFoodStrategiesBySid(int sId);

    /**
     * 删除 攻略-食物表的的指定记录
     * @param foodStrategy
     */
    void removeFoodStrategy(TbFoodStrategy foodStrategy);

    /**
     * 更新 攻略表 (正文、标题、状态)
     * @param strategy
     */
    void updateStrategy(TbStrategy strategy);

    /**
     * 获取发表美食攻略数排行的用户信息
     * @return
     */
    List<TbStrategy> queryUserFromStrategyDesc();

    /**
     * 通过时间倒序获取美食攻略信息
     * @return
     */
    List<TbStrategy> queryStrategyOrderByCreateTime();

    /**
     * 创建新的用户收藏美食攻略记录
     * @param sId
     * @param uId
     */
    void createNewStrategyUser(int sId, int uId);

    /**
     * 通过Uid查询用户自己编写的美食攻略
     * @param uId
     * @return
     */
    List<TbStrategy> queryStrategyByUid(int uId);

    /**
     * 判断是否
     * @param sId
     * @param uId
     * @return
     */
    TbStrategy queryStrategyBySidAndUid(int sId, int uId);
}
