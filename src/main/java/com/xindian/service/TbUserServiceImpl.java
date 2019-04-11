package com.xindian.service;

import com.xindian.dao.TbUserDao;
import com.xindian.pojo.TbUser;
import com.xindian.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao userDao;

    @Override
    public int addUser(TbUser user) {
        return userDao.addUser(user);
    }

    @Override
    public TbUser queryUser(TbUser user) {
        return userDao.queryUser(user);
    }
}
