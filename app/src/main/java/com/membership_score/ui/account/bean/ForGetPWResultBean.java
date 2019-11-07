package com.membership_score.ui.account.bean;

import java.io.Serializable;

public class ForGetPWResultBean implements Serializable {
    private String userName;
    private String password;

    public ForGetPWResultBean(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
