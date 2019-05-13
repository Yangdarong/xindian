package com.xindian.dao;

import com.xindian.pojo.TbUser;
import com.xindian.pojo.TbUserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TbUserDao {

    int addUser(TbUser user);

    TbUser queryUser(TbUser user);

    void updateUser(TbUser user);

    void updateUserHeadPortrait(TbUser user);

    TbUser queryUserByUserName(TbUser user);

    void updateUserSex(TbUser user);

    void updateLoginTime(@Param("uId") int uId, @Param("uLoginTime") Date uLoginTime);

    // 更新登录状态
    void updateUserState(TbUser user);
    void logout(TbUser user);

    List<TbUserAddress> queryUserAddressList(@Param("uId") int uId);

    void createAddressInfo(TbUserAddress address);
}
