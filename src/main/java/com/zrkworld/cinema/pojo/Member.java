package com.zrkworld.cinema.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public class Member implements Serializable {
    /**
     * 与数据库的memberid映射
     */
    private String memberId;

    private String password;

    private String name;

    private String sex;

    private String birthday;

    private static final long serialVersionUID = 1L;

    public String getmemberId() {
        return memberId;
    }

    public void setmemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}