package com.xindian.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.FoodResultType;
import com.xindian.common.FoodsResultType;
import com.xindian.common.UserResultType;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbUser;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class UrlUtils {
    public static final String TYPE_FOOD = "foods";
    public static final String TYPE_MER = "mers";
    public static final String TYPE_USER = "users";

    /**
     * 封装成JSON对象,通过输入流发送请求
     * @param response  response 响应(固定)
     * @param result    JSON 模板
     * @param obj       需要封装的数据
     */
    public static void sendJsonData(HttpServletResponse response, Object result, Object obj) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();

        if (result instanceof UserResultType) {
            UserResultType resultType = (UserResultType) result;
            TbUser user = (TbUser) obj;
            try {
                out = response.getWriter();
                if (user != null) {
                    if (user.getuUserStateId() == 1)
                        resultType.setState(2);
                    resultType.setState(1);
                    resultType.setUser(user);
                } else {
                    resultType.setState(0);
                    resultType.setUser(null);
                }
                out.write(mapper.writeValueAsString(resultType));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result instanceof FoodsResultType) {
            FoodsResultType resultType = (FoodsResultType) result;
            List<TbFood> foods = (List<TbFood>) obj;
            try {
                out = response.getWriter();
                if (foods != null) {
                    resultType.setState(1);
                    resultType.setFoods(foods);
                } else {
                    resultType.setState(0);
                    resultType.setFoods(null);
                }
                out.write(mapper.writeValueAsString(resultType));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result instanceof FoodResultType) {
            FoodResultType resultType = (FoodResultType) result;
            TbFood food = (TbFood) obj;
            try {
                out = response.getWriter();
                if (food != null) {
                    resultType.setState(1);
                    resultType.setFood(food);
                } else {
                    resultType.setState(0);
                    resultType.setFood(null);
                }
                out.write(mapper.writeValueAsString(resultType));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
