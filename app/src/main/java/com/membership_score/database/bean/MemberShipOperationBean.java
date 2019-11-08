package com.membership_score.database.bean;

import java.io.Serializable;

/**
 * 会员积分操作
 * **/
public class MemberShipOperationBean implements Serializable {
    private String member_ship_id;//会员积分ID
    private String member_ship_time;//会员积分操作时间
    private Integer member_ship_pay_type;//会员积分操作类型
    private Integer member_ship_total;//会员积分剩余总数

    public MemberShipOperationBean(String member_ship_id, String member_ship_time,
                                   Integer member_ship_pay_type, Integer member_ship_total) {
        this.member_ship_id = member_ship_id;
        this.member_ship_time = member_ship_time;
        this.member_ship_pay_type = member_ship_pay_type;
        this.member_ship_total = member_ship_total;
    }

    public String getMember_ship_id() {
        return member_ship_id;
    }

    public void setMember_ship_id(String member_ship_id) {
        this.member_ship_id = member_ship_id;
    }

    public String getMember_ship_time() {
        return member_ship_time;
    }

    public void setMember_ship_time(String member_ship_time) {
        this.member_ship_time = member_ship_time;
    }

    public Integer getMember_ship_pay_type() {
        return member_ship_pay_type;
    }

    public void setMember_ship_pay_type(Integer member_ship_pay_type) {
        this.member_ship_pay_type = member_ship_pay_type;
    }

    public Integer getMember_ship_total() {
        return member_ship_total;
    }

    public void setMember_ship_total(Integer member_ship_total) {
        this.member_ship_total = member_ship_total;
    }
}
