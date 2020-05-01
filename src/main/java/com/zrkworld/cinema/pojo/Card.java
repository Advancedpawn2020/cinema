package com.zrkworld.cinema.pojo;

import java.io.Serializable;


/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public class Card implements Serializable {
    /**
     * 与数据库的cardid映射
     */

    private String cardId;
    /**
     * =与数据库的memberid映射
     */

    private String memberId;

    private Integer balance;

    private Integer integral;

    private Integer lose;

    private static final long serialVersionUID = 1L;

    public String getcardId() {
        return cardId;
    }

    public void setcardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getmemberId() {
        return memberId;
    }

    public void setmemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }
}