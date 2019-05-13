package com.xindian.service;

import com.xindian.pojo.TbUser;
import com.xindian.pojo.TbUserAddress;

import java.util.Date;
import java.util.List;

public interface TbUserService {

    int addUser(TbUser user);

    TbUser queryUser(TbUser user);

    TbUser queryUserByUserName(TbUser user);

    void updateUser(TbUser user);

    // 修改登录时间
    void updateLoginTime(TbUser user, Date date);
    // 修改登录状态
    void updateUserState(TbUser user);
    void logout(TbUser user);

    // 修改头像
    void updateUserIcon(TbUser user ,String iConUrl);

    // 修改性别
    void updatUserSex(TbUser user, String sex);

    /**
     * 获取用户的地址表信息
     * @param uId
     * @return
     */
    List<TbUserAddress> queryUserAddressList(int uId);

    /**
     * 创建地址信息
     * @param address
     */
    void createAddressInfo(TbUserAddress address);


    // 修改手机号

    // 修改邮箱地址

    // 修改密码

}
