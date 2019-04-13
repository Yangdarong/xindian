package com.xindian.service;

import com.xindian.pojo.TbUser;

public interface TbUserService {

    int addUser(TbUser user);

    TbUser queryUser(TbUser user);

    TbUser queryUserByUserName(TbUser user);

    void updateUser(TbUser user);

    // 修改头像
    void updateUserIcon(TbUser user ,String iConUrl);

    // 修改性别
    void updatUserSex(TbUser user, String sex);

    // 修改手机号

    // 修改邮箱地址

    // 修改密码

}
