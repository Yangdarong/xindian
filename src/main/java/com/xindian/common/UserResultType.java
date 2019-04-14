package com.xindian.common;

import com.xindian.pojo.TbUser;

/**
 *  查询用户的json 返回结果
 */
public class UserResultType {
    private int state;  // 1 返回成功 0 返回失败

    private TbUser user;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public TbUser getUser() {
        return user;
    }

    public void setUser(TbUser user) {
        this.user = user;
    }
}
