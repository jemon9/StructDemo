package com.heyha.www.structdemo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Heyha on 2017/1/20.
 */

public class RegisterBean extends BmobObject {
    public String userName;
    public String userEmail;
    public String userPswd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }
}
