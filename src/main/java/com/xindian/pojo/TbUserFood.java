package com.xindian.pojo;

public class TbUserFood {
    private int ufId;
    private int uId;
    private int fId;

    private TbUser user;
    private TbFood food;

    public TbUserFood() {
    }

    public int getUfId() {
        return ufId;
    }

    public void setUfId(int ufId) {
        this.ufId = ufId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public TbUser getUser() {
        return user;
    }

    public void setUser(TbUser user) {
        this.user = user;
    }

    public TbFood getFood() {
        return food;
    }

    public void setFood(TbFood food) {
        this.food = food;
    }
}
