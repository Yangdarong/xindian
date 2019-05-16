package com.xindian.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.*;
import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbStrategy;
import com.xindian.pojo.TbUser;
import com.xindian.pojo.TbUserAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UrlUtils {
    public static final String TYPE_FOOD = "foods";
    public static final String TYPE_MER = "mers";
    public static final String TYPE_USER = "users";

    /*----------------------------------------request获取body----------------------------------------------------*/
    /**
     * 获取写入到request的JSON字节数组
     * @param request
     * @return
     * @throws IOException
     */
    private static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }

        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength;) {
            int readLen = request.getInputStream().read(buffer, i, contentLength -1);
            if (readLen == -1) {
                break;
            }

            i += readLen;
        }
        return buffer;
    }

    /**
     * request字节数据转化成String
     * @param request
     * @return
     * @throws IOException
     */
    private static String getRequestPostStr(HttpServletRequest request) throws IOException {
        byte[] buffer = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        assert buffer != null;
        return new String(buffer, charEncoding);
    }

    public static String getRequestJsonString(HttpServletRequest request) throws IOException {
        String submitMethod = request.getMethod();
        if (submitMethod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8")
                    .replace("%22", "\\");
        } else {
            return getRequestPostStr(request);
        }
    }

    /*----------------------------------------request接受JSON数据----------------------------------------------------*/
    /**
     * 接受JSON数据转化成Bean对象
     * @param content
     * @param valueType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T jsonToBean(String content, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, valueType);
    }

    /*----------------------------------------JSON发送数据----------------------------------------------------*/
    /**
     * 返回标准JSON信息
     * @param response  response 响应(固定)
     * @param state     设置状态码
     * @param message   设置消息内容
     */
    public static void sendJsonData(HttpServletResponse response, int state, String message) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();

        CommonResultType resultType = new CommonResultType();
        try {
            out = response.getWriter();
            resultType.setState(state);
            resultType.setMessage(message);
            out.write(mapper.writeValueAsString(resultType));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 封装成JSON对象,通过输入流发送请求
     * @param response  response 响应(固定)
     * @param result    JSON 模板
     * @param obj       需要封装的数据 单个或单条数据
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
                mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
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
        } else if (result instanceof UserAddressResultType) {
            UserAddressResultType resultType = (UserAddressResultType) result;
            List<TbUserAddress> addresses = (List<TbUserAddress>) obj;
            try {
                out = response.getWriter();
                if (addresses != null) {
                    resultType.setState(1);
                    resultType.setAddresses(addresses);
                } else {
                    resultType.setState(0);
                    resultType.setAddresses(null);
                }
                out.write(mapper.writeValueAsString(resultType));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result instanceof StrategiesResultType) {
            StrategiesResultType resultType = (StrategiesResultType) result;
            List<TbStrategy> strategies = (List<TbStrategy>) obj;
            try {
                out = response.getWriter();
                if (strategies != null) {
                    resultType.setState(1);
                    resultType.setStrategies(strategies);
                    resultType.setMessage("拿到数据了");
                } else {
                    resultType.setState(0);
                    resultType.setStrategies(null);
                    resultType.setMessage("没有拿到数据");
                }
                out.write(mapper.writeValueAsString(resultType));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
