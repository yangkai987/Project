package com.membership_score.database.bean;

import java.io.Serializable;

public class RegisterDBBean implements Serializable {
    private Integer  adminLevel;//等级
    private String   adminId;//会员ID
    private Integer  adminSex;//会员性别
    private String   adminNickname;//会员昵称
    private String   adminPhoneNum;//会员电话
    private String   adminIcon;//会员头像
    private String   adminAddress;//会员 地址
    private String   adminRegisterTime;//会员 注册时间
    private String   adminLoginName;//会员 登陆用户名
    private String   adminLoginPassWord;//会员 登陆密码

    public RegisterDBBean(Integer adminLevel, String adminId, Integer adminSex, String adminNickname,
                          String adminPhoneNum, String adminIcon, String adminAddress,
                          String adminRegisterTime, String adminLoginName, String adminLoginPassWord) {
        this.adminLevel = adminLevel;
        this.adminId = adminId;
        this.adminSex = adminSex;
        this.adminNickname = adminNickname;
        this.adminPhoneNum = adminPhoneNum;
        this.adminIcon = adminIcon;
        this.adminAddress = adminAddress;
        this.adminRegisterTime = adminRegisterTime;
        this.adminLoginName = adminLoginName;
        this.adminLoginPassWord = adminLoginPassWord;
    }

    public String getAdminNickname() {
        return adminNickname;
    }

    public void setAdminNickname(String adminNickname) {
        this.adminNickname = adminNickname;
    }

    public String getAdminLoginName() {
        return adminLoginName;
    }

    public void setAdminLoginName(String adminLoginName) {
        this.adminLoginName = adminLoginName;
    }

    public String getAdminLoginPassWord() {
        return adminLoginPassWord;
    }

    public void setAdminLoginPassWord(String adminLoginPassWord) {
        this.adminLoginPassWord = adminLoginPassWord;
    }

    public Integer getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(Integer adminLevel) {
        this.adminLevel = adminLevel;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(Integer adminSex) {
        this.adminSex = adminSex;
    }

    public String getAdminPhoneNum() {
        return adminPhoneNum;
    }

    public void setAdminPhoneNum(String adminPhoneNum) {
        this.adminPhoneNum = adminPhoneNum;
    }

    public String getAdminIcon() {
        return adminIcon;
    }

    public void setAdminIcon(String adminIcon) {
        this.adminIcon = adminIcon;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminRegisterTime() {
        return adminRegisterTime;
    }

    public void setAdminRegisterTime(String adminRegisterTime) {
        this.adminRegisterTime = adminRegisterTime;
    }
}
