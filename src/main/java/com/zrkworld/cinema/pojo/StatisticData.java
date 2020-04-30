package com.zrkworld.cinema.pojo;

public class StatisticData {
    Long memberNum;
    Long cardNum;
    Long movieNum;

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
