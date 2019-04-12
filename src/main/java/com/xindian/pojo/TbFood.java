package com.xindian.pojo;

/**
 *  菜品
 */
public class TbFood {

    private int fId;
    private int fFtId;
    private int fMid;

    private String fName;
    private String fUrl;    // 保存图片地址
    private Float fPrice;
    private Float fDPrice;

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public int getfFtId() {
        return fFtId;
    }

    public void setfFtId(int fFtId) {
        this.fFtId = fFtId;
    }

    public int getfMid() {
        return fMid;
    }

    public void setfMid(int fMid) {
        this.fMid = fMid;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfUrl() {
        return fUrl;
    }

    public void setfUrl(String fUrl) {
        this.fUrl = fUrl;
    }

    public Float getfPrice() {
        return fPrice;
    }

    public void setfPrice(Float fPrice) {
        this.fPrice = fPrice;
    }

    public Float getfDPrice() {
        return fDPrice;
    }

    public void setfDPrice(Float fDPrice) {
        this.fDPrice = fDPrice;
    }
}
