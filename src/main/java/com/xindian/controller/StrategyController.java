package com.xindian.controller;

import com.xindian.common.FoodsResultType;
import com.xindian.common.StrategiesResultType;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodStrategy;
import com.xindian.pojo.TbStrategy;
import com.xindian.service.TbStrategyService;
import com.xindian.utils.UrlUtils;
import com.xindian.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private TbStrategyService service;

    private List<TbStrategy> strategies;

    /*-----------------------------安卓端-----------------------------------*/
    /**
     * 用户发布美食攻略, 首先创建一个记录
     * @param request
     * @param response
     */
    @RequestMapping("/createStrategy.json")
    public void createNewStrategy(HttpServletRequest request, HttpServletResponse response) {
        // 设置攻略状态
        int uId = Integer.parseInt(request.getParameter("uId"));

        service.createNewStrategy(ValueUtils.STRATEGY_ON_EDITING, uId);
        int sId = service.queryIncreaseId();
        TbStrategy strategy = service.queryStrategyBySid(sId);
        strategies = new ArrayList<>();
        strategies.add(strategy);

        // JSON 返回创建的 实体对象
        StrategiesResultType resultType = new StrategiesResultType();
        UrlUtils.sendJsonData(response, resultType, strategies);
    }

    /**
     * 用户在编辑中取消美食攻略的发布
     * @param response
     * @param strategy
     */
    @RequestMapping("/cancelStrategy.json")
    public void cancelCreatedStrategy(HttpServletResponse response, TbStrategy strategy) {
        if (strategy != null && strategy.getsId() != 0) {

            int sId = strategy.getsId();
            service.updateStrategyStateBySid(sId, ValueUtils.STRATEGY_INVALID);

            UrlUtils.sendJsonData(response, 1, "成功了");
        } else {
            UrlUtils.sendJsonData(response, 0, "失败,请进行调试");
        }
    }

    /**
     * 编辑过程中,添加美食信息
     * @param request
     * @param response
     */
    @RequestMapping("/createFoodStrategy.json")
    public void createNewFoodStrategy(HttpServletRequest request, HttpServletResponse response) {
        int sId = Integer.parseInt(request.getParameter("sId"));
        int fId = Integer.parseInt(request.getParameter("fId"));

        TbFoodStrategy foodStrategy = service.queryExistFoodStrategy(sId, fId);
        if (foodStrategy == null) {
            service.createNewFoodStrategy(sId, fId);
            UrlUtils.sendJsonData(response, 1, "添加成功");
        } else {
            UrlUtils.sendJsonData(response, 0, "该商品已经加入攻略中!");
        }
    }

    /**
     * 通过 sId 获取攻略附加的食物信息
     * @param request
     * @param response
     */
    @RequestMapping("/updateMyNewStrategy.json")
    public void updateMyNewStrategy(HttpServletRequest request, HttpServletResponse response) {
        int sId = Integer.parseInt(request.getParameter("sId"));
        List<TbFood> foods = service.queryFoodStrategiesBySid(sId);
        FoodsResultType resultType = new FoodsResultType();

        UrlUtils.sendJsonData(response, resultType, foods);
    }

    /**
     * 通过fId 和 sId 删除美食攻略中附加的记录
     * @param response
     */
    @RequestMapping("/removeFoodFromStrategy.json")
    public void removeFoodStrategy(HttpServletResponse response, TbFoodStrategy foodStrategy) {

        service.removeFoodStrategy(foodStrategy);
        UrlUtils.sendJsonData(response, 1, "删除记录成功!");
    }

    /**
     * 更新美食攻略的正文和标题,修改状态为发布
     * @param response
     */
    @RequestMapping("/publishStrategy.json")
    public void publishStrategy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = UrlUtils.getRequestJsonString(request);
        StrategiesResultType resultType = UrlUtils.jsonToBean(json, StrategiesResultType.class);
        List<TbStrategy> strategies = resultType.getStrategies();

        if (strategies != null) {
            TbStrategy strategy = strategies.get(0);
            strategy.setsState(ValueUtils.STRATEGY_ON_PUBLISHING);
            strategy.setsCreateTime(new Timestamp(System.currentTimeMillis()));
            service.updateStrategy(strategy);

            UrlUtils.sendJsonData(response, 1, "发布成功");
        } else {
            UrlUtils.sendJsonData(response, 0, "发布失败");
        }

    }

    /**
     * 获取用户推荐表
     * @param response
     */
    @RequestMapping("/queryRecommendUsers.json")
    public void queryRecommendUsers(HttpServletResponse response) {
        StrategiesResultType resultType = new StrategiesResultType();
        List<TbStrategy> strategies = service.queryUserFromStrategyDesc();
        if (strategies != null) {
            resultType.setState(1);
            resultType.setStrategies(strategies);
            resultType.setMessage("获取消息列表成功");
        } else {
            resultType.setState(0);
            resultType.setStrategies(strategies);
            resultType.setMessage("获取消息列表成功");
        }
    }
}
