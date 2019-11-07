package com.membership_score.database.bean;

import java.io.Serializable;


/**
 * 会员积分列表
 */
public class MemberShipScoreNum implements Serializable {
    private int  id;
    private String  user_id;
    private Integer membership_score_pay_money_num;//充值金额
    private Integer membership_score_get_num;//充值积分
    private String membership_score_pay_time;//积分时间
    private Integer ms_pay_type;//充值类型

    public MemberShipScoreNum(int id,String user_id, Integer membership_score_pay_money_num, Integer membership_score_get_num, String membership_score_pay_time, Integer ms_pay_type) {
        this.id = id;
        this.user_id = user_id;
        this.membership_score_pay_money_num = membership_score_pay_money_num;
        this.membership_score_get_num = membership_score_get_num;
        this.membership_score_pay_time = membership_score_pay_time;
        this.ms_pay_type = ms_pay_type;
    }

    public MemberShipScoreNum(String user_id, Integer membership_score_pay_money_num, Integer membership_score_get_num, String membership_score_pay_time, Integer ms_pay_type) {
        this.user_id = user_id;
        this.membership_score_pay_money_num = membership_score_pay_money_num;
        this.membership_score_get_num = membership_score_get_num;
        this.membership_score_pay_time = membership_score_pay_time;
        this.ms_pay_type = ms_pay_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getMembership_score_pay_money_num() {
        return membership_score_pay_money_num;
    }

    public void setMembership_score_pay_money_num(Integer membership_score_pay_money_num) {
        this.membership_score_pay_money_num = membership_score_pay_money_num;
    }

    public Integer getMembership_score_get_num() {
        return membership_score_get_num;
    }

    public void setMembership_score_get_num(Integer membership_score_get_num) {
        this.membership_score_get_num = membership_score_get_num;
    }

    public String getMembership_score_pay_time() {
        return membership_score_pay_time;
    }

    public void setMembership_score_pay_time(String membership_score_pay_time) {
        this.membership_score_pay_time = membership_score_pay_time;
    }

    public Integer getMs_pay_type() {
        return ms_pay_type;
    }

    public void setMs_pay_type(Integer ms_pay_type) {
        this.ms_pay_type = ms_pay_type;
    }
}
