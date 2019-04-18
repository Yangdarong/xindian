package com.xindian.common;

import com.xindian.pojo.TbMer;

public class MerLoginResultType {

    private int state;  // 取到1 没有0
    private TbMer mer;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public TbMer getMer() {
        return mer;
    }

    public void setMer(TbMer mer) {
        this.mer = mer;
    }
}
