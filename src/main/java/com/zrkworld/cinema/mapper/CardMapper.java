package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.Card;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public interface CardMapper {
    /**
     * 根据会员id，查询该会员所有的会员卡
     * @param memberId
     * @return
     */
    List<Card> selectCardByMemberId(String memberId);

    /**
     * 新增会员卡
     * @param card
     */
    void insertCard(Card card);

    /**
     * 通过会员卡号，查找会员卡
     * @param cardId
     * @return
     */
    Card selectCardById(String cardId);

    /**
     * 通过会员卡号，删除会员卡
     * @param cardId
     */
    void deleteCardById(String cardId);

    /**
     * 通过卡号来更新会员卡
     * @param card
     */
    void updateCardById(Card card);

    /**
     * 所有会员卡的数量
     * @return
     */
    Long selectCardCount();

    List<String> selectCardIdByFuzzyQuery(String memberId,String cardId);

}