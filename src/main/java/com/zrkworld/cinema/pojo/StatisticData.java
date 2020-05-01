package com.zrkworld.cinema.pojo;

import java.io.Serializable;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 * 对总消费额度的记录的封装
 */
public class StatisticData implements Serializable {
    Long memberNum;
    Long cardNum;
    Long movieNum;
    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        return "StatisticData{" +
                "memberNum=" + memberNum +
                ", cardNum=" + cardNum +
                ", movieNum=" + movieNum +
                '}';
    }

    public Long getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Long memberNum) {
        this.memberNum = memberNum;
    }

    public Long getCardNum() {
        return cardNum;
    }

    public void setCardNum(Long cardNum) {
        this.cardNum = cardNum;
    }

    public Long getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(Long movieNum) {
        this.movieNum = movieNum;
    }
}
