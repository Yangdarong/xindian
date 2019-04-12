package com.xindian.service;

import com.xindian.pojo.TbUser;

public interface TbUserService {

    int addUser(TbUser user);

    TbUser queryUser(TbUser user);

    void updateUser(TbUser user);

    // 修改头像
    void updateUserIcon(TbUser user ,String iConUrl);
}
