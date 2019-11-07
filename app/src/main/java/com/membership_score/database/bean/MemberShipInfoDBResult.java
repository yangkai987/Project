package com.membership_score.database.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 会员列表
 */
public class MemberShipInfoDBResult implements Serializable {
    private int  id;
    private Integer  level;//等级
    private String  userId;
    private Integer  sex;//性别
    private String   name;//会员名称
    private String   phoneNum;//会员电话
    private String   icon;//会员头像
    private String   address;//会员 地址
    private Integer   ms_total_num;//会员总积分积分
    private Integer   ms_last_time_num;//会员上一次积分
    private String   ms_last_time;//上一次积分时间
    private List<MemberShipScoreNumResult> ms_list;

    public MemberShipInfoDBResult(String userId,Integer sex,Integer level, String name, String phoneNum, String icon,
                                  String address, Integer ms_total_num, Integer ms_last_time_num, String ms_last_time, List<MemberShipScoreNumResult> ms_list) {
        this.userId = userId;
        this.sex = sex;
        this.level = level;
        this.name = name;
        this.phoneNum = phoneNum;
        this.icon = icon;
        this.address = address;
        this.ms_total_num = ms_total_num;
        this.ms_last_time_num = ms_last_time_num;
        this.ms_last_time = ms_last_time;
        this.ms_list = ms_list;
    }

    public MemberShipInfoDBResult(String userId,Integer sex,Integer level, String name, String phoneNum, String icon,
                                  String address, Integer ms_total_num, Integer ms_last_time_num, String ms_last_time) {
        this.userId = userId;
        this.level = level;
        this.sex = sex;
        this.name = name;
        this.phoneNum = phoneNum;
        this.icon = icon;
        this.address = address;
        this.ms_total_num = ms_total_num;
        this.ms_last_time_num = ms_last_time_num;
        this.ms_last_time = ms_last_time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMs_total_num() {
        return ms_total_num;
    }

    public void setMs_total_num(Integer ms_total_num) {
        this.ms_total_num = ms_total_num;
    }

    public Integer getMs_last_time_num() {
        return ms_last_time_num;
    }

    public void setMs_last_time_num(Integer ms_last_time_num) {
        this.ms_last_time_num = ms_last_time_num;
    }

    public String getMs_last_time() {
        return ms_last_time;
    }

    public void setMs_last_time(String ms_last_time) {
        this.ms_last_time = ms_last_time;
    }

    public List<MemberShipScoreNumResult> getMs_list() {
        return ms_list;
    }

    public void setMs_list(List<MemberShipScoreNumResult> ms_list) {
        this.ms_list = ms_list;
    }
}
