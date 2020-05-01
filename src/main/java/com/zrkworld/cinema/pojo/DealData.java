package com.zrkworld.cinema.pojo;

import java.io.Serializable;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 * 封装交易额总量
 */
public class DealData implements Serializable {
    private static final long serialVersionUID = 1L;
    Long consume;
    Long recharge;
    Long integral;

    @Override
    public String toString() {
        return "DealData{" +
                "consume=" + consume +
                ", recharge=" + recharge +
                ", integral=" + integral +
                '}';
    }

    public Long getConsume() {
        return consume;
    }

    public void setConsume(Long consume) {
        this.consume = consume;
    }

    public Long getRecharge() {
        return recharge;
    }

    public void setRecharge(Long recharge) {
        this.recharge = recharge;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }
}
