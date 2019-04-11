package com.xindian.service;

import com.xindian.pojo.TbUser;

public interface TbUserService {

    int addUser(TbUser user);

    TbUser queryUser(TbUser user);
}
