package com.xindian.pojo;

public class TbStrategyUser {
    private int suId;
    private int sId;
    private int uId;

    private TbStrategy strategy;
    private TbUser user;

    public TbStrategyUser() {
    }

    public int getSuId() {
        return suId;
    }

    public void setSuId(int suId) {
        this.suId = suId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public TbStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(TbStrategy strategy) {
        this.strategy = strategy;
    }

    public TbUser getUser() {
        return user;
    }

    public void setUser(TbUser user) {
        this.user = user;
    }
}
