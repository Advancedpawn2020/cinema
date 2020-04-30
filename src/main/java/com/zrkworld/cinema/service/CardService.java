package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.Card;

import java.util.List;

public interface CardService {
    List<Card> getCardData(String memberId);

    Card getCardDataById(String cardId);
    void registerCard(String cardId);
    void deleteCardById(String cardId);
    String reissueCard(String cardId);

    void loseCard(String cardId);

    void cancelCard(String cardId);

    void rechargeCard(String cardId, int parseInt);

    void consumeCard(String cardId, int price, int integral);

    void exchangeIntegral(String memberId, int parseInt);
}
