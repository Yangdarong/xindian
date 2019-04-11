package com.xindian.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 *  用户表
 */
public class TbUser {

    @JsonIgnore
    private int uId;

    private String uLoginId;

    @JsonIgnore
    private String uPassword;

    private String uSignature;

    private String uSex;

    private String uMail;

    private String uPhone;

    private String uHeadPortrait;

    private Date uRegisterTime;

    private Date uLoginTime;

    private int uUserStateId;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuLoginId() {
        return uLoginId;
    }

    public void setuLoginId(String uLoginId) {
        this.uLoginId = uLoginId;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuSignature() {
        return uSignature;
    }

    public void setuSignature(String uSignature) {
        this.uSignature = uSignature;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public String getuMail() {
        return uMail;
    }

    public void setuMail(String uMail) {
        this.uMail = uMail;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuHeadPortrait() {
        return uHeadPortrait;
    }

    public void setuHeadPortrait(String uHeadPortrait) {
        this.uHeadPortrait = uHeadPortrait;
    }

    public Date getuRegisterTime() {
        return uRegisterTime;
    }

    public void setuRegisterTime(Date uRegisterTime) {
        this.uRegisterTime = uRegisterTime;
    }

    public Date getuLoginTime() {
        return uLoginTime;
    }

    public void setuLoginTime(Date uLoginTime) {
        this.uLoginTime = uLoginTime;
    }

    public int getuUserStateId() {
        return uUserStateId;
    }

    public void setuUserStateId(int uUserStateId) {
        this.uUserStateId = uUserStateId;
    }
}
