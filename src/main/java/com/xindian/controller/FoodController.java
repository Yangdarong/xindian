package com.xindian.controller;

import com.xindian.common.FoodResultType;
import com.xindian.common.FoodsResultType;
import com.xindian.common.PageBean;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbFoodType;
import com.xindian.pojo.TbUserFood;
import com.xindian.service.TbFoodService;
import com.xindian.utils.FileUtils;
import com.xindian.utils.UrlUtils;
import com.xindian.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private TbFoodService service;

    /*-----------------------------安卓端-----------------------------------*/

    @RequestMapping("/getFood.json")
    public void getFoodById(HttpServletRequest request, HttpServletResponse response, TbFood food) {
        food = service.getFoodById(food.getfId());

        FoodResultType result = new FoodResultType();
        UrlUtils.sendJsonData(response, result, food);
    }

    /**
     * 安卓端
     * 通过类别查找需要的菜品数据
     *
     * 返回 json 格式的数据
     * @param request
     * @param response
     * json 格式的数据
     */
    @RequestMapping("/getFoodsList.json")
    public void getFoodListByFtId(HttpServletRequest request, HttpServletResponse response, TbFood food) {

        List<TbFood> foods = service.queryFoodByFtId(food.getFtId());

        FoodsResultType result = new FoodsResultType();
        UrlUtils.sendJsonData(response, result, foods);
    }

    /**
     * 通过关键字检索食物信息
     * @param request
     * @param response
     */
    @RequestMapping("/queryFoodsBykW.json")
    public void queryFoodsByKw(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String json = UrlUtils.getRequestJsonString(request);
        FoodResultType resultType = UrlUtils.jsonToBean(json, FoodResultType.class);
        String fName = resultType.getFood().getfName();
        List<TbFood> foods = new ArrayList<>();
        FoodsResultType type = new FoodsResultType();
        if (!ValueUtils.isNull(fName)) {
            foods = service.queryFoodsInfoByName(fName);
        }
        UrlUtils.sendJsonData(response, type, foods);

    }

    @RequestMapping("/collectMenu.json")
    public void collectMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = UrlUtils.getRequestJsonString(request);
        FoodsResultType resultType = UrlUtils.jsonToBean(json, FoodsResultType.class);
        int uId = resultType.getState();
        List<TbFood> foods = resultType.getFoods();
        int count = 0;
        for (TbFood food : foods) {
            // 获取每个fId;
            int fId = food.getfId();
            // 查询收藏表是否有该条记录
            TbUserFood userFood = service.queryUserFood(uId, fId);
            if (userFood != null) {
                count++;
            } else {
                service.createUserFood(uId, fId);
            }
        }
        if (count == foods.size()) {    // 所有食物均已收藏
            UrlUtils.sendJsonData(response, 0, "食物列表已经收藏过了");
        } else if (count == 0) {        // 收藏成功
            UrlUtils.sendJsonData(response, 1, "收藏成功");
        } else {                        // 部分食物已收藏
            UrlUtils.sendJsonData(response, 1, "已添加新的收藏");
        }
    }

    @RequestMapping("/queryFoodsByTime.json")
    public void queryFoodsByTime(HttpServletResponse response) {
        List<TbFood> foods = service.queryAllFoodsInfo();
        Collections.reverse(foods);

        List<TbFood> dFoods = foods.subList(0, 4);
        FoodsResultType resultType = new FoodsResultType();
        UrlUtils.sendJsonData(response, resultType, dFoods);
    }

    /*-----------------------------管理端-----------------------------------*/
    /**
     * 管理端
     * @param request
     * @param food
     * @return
     */
    @RequestMapping("/editFoodInfo")
    public String editFoodInfo(HttpServletRequest request, TbFood food) {
        String s = request.getParameter("merId");
        int mId = Integer.parseInt(s);
        service.editFoodInfo(food);

        return "redirect:/food/queryFoods.do?mId=" + mId;
    }

    /**
     * 管理端
     * 根据ID查找食物
     * @param request
     * @return
     */
    @RequestMapping("/editFood")
    public String queryFoodById(HttpServletRequest request) {
        String s = request.getParameter("fId");
        int fid = Integer.parseInt(s);

        TbFood tbFood = service.queryFoodById(fid);
        HttpSession session = request.getSession();
        if (session.getAttribute("tbFood") == null)
            session.setAttribute("tbFood", tbFood);
        else {
            session.removeAttribute("tbFood");
            session.setAttribute("tbFood", tbFood);
        }
        return "redirect:/page/editFood";
    }

    /**
     * 管理端
     * 查询所有食物信息(默认第一页)
     * @param request
     * @return
     */
    @RequestMapping("/queryFoods.do")
    public String queryAllFoodsInfo(HttpServletRequest request) {
        String s = request.getParameter("mId");
        int mId = Integer.parseInt(s);
        PageBean<TbFood> pageBean = service.queryAllFoodsInfoFindPage(1, mId);


        HttpSession session = request.getSession();
        if (pageBean != null) {
            addToSession(session, pageBean);
        }


        // 获取所有菜品种类
        List<TbFoodType> types = service.queryAllTypes();
        if (types != null && session.getAttribute("types") == null)
            session.setAttribute("types", types);
        return "redirect:/page/merFood";
    }

    /**
     * 管理端
     * 修改食物的图片
     * @param request
     * @param pictureFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/editFoodPic")
    public String changeFoodPicture(HttpServletRequest request, MultipartFile pictureFile) throws IOException {
        String s = request.getParameter("fId");
        int fId = Integer.parseInt(s);
        String fUrl = FileUtils.uploadPicture(pictureFile, request, UrlUtils.TYPE_FOOD);

        service.changeFoodPicture(fId, fUrl);
        return "redirect:/food/editFood?fId=" + fId;
    }

    /**
     * 管理端
     * 增加菜品带图片
     * @param request
     * @param food
     * @param pictureFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/addFood")
    public String addFoodWithPic(HttpServletRequest request, TbFood food, MultipartFile pictureFile) throws IOException {
        String s = request.getParameter("mId");
        int mId = Integer.parseInt(s);

        String fUrl = FileUtils.uploadPicture(pictureFile, request, UrlUtils.TYPE_FOOD);

        food.setfUrl(fUrl);
        food.setmId(mId);
        service.addFood(food);

        return "redirect:/food/queryFoods.do?mId=" + mId;
    }

    /**
     * 管理端
     * 根据分页查找菜单
     * @param request
     * @return
     */
    @RequestMapping("/queryPage.do")
    public String queryFoodsByPage(HttpServletRequest request) {
        String s = request.getParameter("mId");
        int mId = Integer.parseInt(s);
        String s1 = request.getParameter("pid");
        int pid = Integer.parseInt(s1);

        PageBean<TbFood> pageBean = service.queryAllFoodsInfoFindPage(pid, mId);
        HttpSession session = request.getSession();
        if (pageBean != null) {
            addToSession(session, pageBean);
        }
        return "redirect:/page/merFood";
    }

    /**
     * 管理端
     * @param session
     * @param pageBean
     */
    private void addToSession(HttpSession session, PageBean<TbFood> pageBean) {
//                session.setAttribute("mer", mer);
        if (session.getAttribute("pageBean") == null) {  // 如果为空则添加
            session.setAttribute("pageBean", pageBean);
        } else {    // 替换session里面的用户对象
            session.removeAttribute("pageBean");
            session.setAttribute("pageBean", pageBean);   // 重新添加
        }
    }

}
