package com.xindian.dao;

import com.xindian.pojo.TbUser;

public interface TbUserDao {

    int addUser(TbUser user);

    TbUser queryUser(TbUser user);

    void updateUser(TbUser user);

    void updateUserHeadPortrait(TbUser user);

    TbUser queryUserByUserName(TbUser user);

    void updateUserSex(TbUser user);

}
