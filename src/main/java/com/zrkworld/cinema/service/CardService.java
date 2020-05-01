package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.Card;

import java.util.List;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public interface CardService {
    /**
     * 查询当前会员的所有会员卡数据
     * @param memberId
     * @return
     */
    List<Card> getCardData(String memberId);

    /**
     * 查询当前会员卡的信息
     * @param cardId
     * @return
     */
    Card getCardDataById(String cardId);

    /**
     * 注册一个新会员卡
     * @param cardId
     */
    void registerCard(String cardId);

    /**
     * 删除一个会员卡信息
     * @param cardId
     */
    void deleteCardById(String cardId);

    /**
     * 补卡，根据cardId，删除会员卡，并将其信息转移到新卡上
     * @param cardId
     * @return
     */
    String reissueCard(String cardId);

    /**
     * 挂失
     * @param cardId
     */
    void loseCard(String cardId);

    /**
     * 解挂
     * @param cardId
     */
    void cancelCard(String cardId);

    /**
     * 充值卡
     * @param cardId
     * @param parseInt 需充值金额
     */
    void rechargeCard(String cardId, int parseInt);

    /**
     * 对卡内金额进行消费
     * @param cardId 卡号
     * @param price 电影票价格
     * @param integral 增加的积分
     */
    void consumeCard(String cardId, int price, int integral);

    /**
     * 积分兑换
     * @param memberId
     * @param parseInt 需消耗的积分数
     */
    void exchangeIntegral(String memberId, int parseInt);

    /**
     * 查询所有注册的会员卡数目
     * @return
     */
    Long getCardNum();

    /**
     * 模糊查询卡号，返回卡号集合
     * @param memberId 会员id
     * @param cardId 卡号
     * @return
     */
    List<String> getCardIdByFuzzyQuery(String memberId,String cardId);

    /**
     * 根据卡号，查到会员卡信息
     * @param cardId
     * @return
     */
    Card getCardByCardId(String cardId);
}
