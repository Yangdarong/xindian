package com.xindian.service;

import com.xindian.dao.TbUserDao;
import com.xindian.pojo.TbUser;
import com.xindian.pojo.TbUserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public TbUser queryUserByUserName(TbUser user) {
        return userDao.queryUserByUserName(user);
    }

    @Override
    public void updateUser(TbUser user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateLoginTime(TbUser user, Date date) {
        userDao.updateLoginTime(user.getuId(), date);
    }

    @Override
    public void updateUserState(TbUser user) {
        userDao.updateUserState(user);
    }

    @Override
    public void logout(TbUser user) {
        userDao.logout(user);
    }

    @Override
    public void updateUserIcon(TbUser user, String iConUrl) {
        user.setuHeadPortrait(iConUrl);
        userDao.updateUserHeadPortrait(user);
    }

    @Override
    public void updatUserSex(TbUser user, String sex) {
        user.setuSex(sex);
        userDao.updateUserSex(user);
    }

    @Override
    public List<TbUserAddress> queryUserAddressList(int uId) {
        return userDao.queryUserAddressList(uId);
    }

    @Override
    public void createAddressInfo(TbUserAddress address) {
        userDao.createAddressInfo(address);
    }
}
