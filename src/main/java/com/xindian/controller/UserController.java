package com.xindian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindian.common.UserAddressResultType;
import com.xindian.common.UserResultType;
import com.xindian.pojo.TbUser;
import com.xindian.pojo.TbUserAddress;
import com.xindian.service.TbUserService;
import com.xindian.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TbUserService userService;

    /*-----------------------------安卓端-----------------------------------*/

    @RequestMapping("/createUserAddress.json")
    public void createAddressInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*if (address.getUaAddress() == null) {
            String uaAddress = request.getParameter("uaAddress");
            byte[] rawBytes = uaAddress.getBytes("ISO-8859-1");
            String address1 = new String(rawBytes,"UTF-8");
            address.setUaAddress(address1);
        } else {

        }*/
        UserAddressResultType resultType;
        String json = UrlUtils.getRequestJsonString(request);
        resultType = UrlUtils.jsonToBean(json, UserAddressResultType.class);

        if (resultType.getAddresses() != null) {
            List<TbUserAddress> addresses = resultType.getAddresses();
            TbUserAddress address = addresses.get(0);
            userService.createAddressInfo(address);
        }
        UrlUtils.sendJsonData(response, 1, "成功");
    }

    @RequestMapping("/queryUserAddress.json")
    public void queryUserAddressList(HttpServletResponse response, TbUser user) {
        List<TbUserAddress> addresses = userService.queryUserAddressList(user.getuId());
        UserAddressResultType result = new UserAddressResultType();
        UrlUtils.sendJsonData(response, result, addresses);
    }

    /**
     * 用户注销
     * @param response
     * @param request
     * @param user
     */
    @RequestMapping("/logout.json")
    public void setUserState(HttpServletResponse response, HttpServletRequest request, TbUser user) {
        user.setuUserStateId(0);
        userService.updateUserState(user);
        UrlUtils.sendJsonData(response, 1, "成功");
    }

    /**
     * 安卓端登录
     * @param request
     * @param response
     * @throws JsonProcessingException
     */
    @RequestMapping("/queryUser.json")
    public void queryUserFromClient(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String uLoginId = request.getParameter("uLoginId");
        String uPassword = request.getParameter("uPassword");


        TbUser user = new TbUser();
        user.setuLoginId(uLoginId);
        user.setuPassword(uPassword);
        user = userService.queryUser(user);
        if (user.getuUserStateId() == 0) {
            user.setuUserStateId(1);
            userService.updateLoginTime(user, new Timestamp(new Date().getTime()));
            userService.updateUserState(user);
        }
        UserResultType result = new UserResultType();
        UrlUtils.sendJsonData(response, result, user);

    }

    @RequestMapping("/addUser.json")
    public void addUserFromClient(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        String uLoginId = request.getParameter("uLoginId");
        String uPassword = request.getParameter("uPassword");

        TbUser user = new TbUser();
        user.setuLoginId(uLoginId);

        response.setContentType("application/json");
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();
        UserResultType result = new UserResultType();

        try {
            out = response.getWriter();

            if (userService.queryUserByUserName(user) == null) {  // 用户没有注册
                user.setuPassword(uPassword);
                userService.addUser(user);
                result.setState(1);
                result.setUser(user);
            } else {    // 用户已经注册
                result.setState(0);
                result.setUser(null);
            }

            out.write(mapper.writeValueAsString(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*-----------------------------服务端-----------------------------------*/
}
