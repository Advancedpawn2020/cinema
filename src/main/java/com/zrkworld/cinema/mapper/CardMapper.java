package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.Card;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardMapper {
    List<Card> selectCardByMemberId(String memberId);

    void insertCard(Card card);

    Card selectCardById(String cardId);

    void deleteCardById(String cardId);

    void updateCardById(Card card);
/*    int countByExample(CardExample example);

    int deleteByExample(CardExample example);

    int deleteByPrimaryKey(String cardid);

    int insert(Card record);

    int insertSelective(Card record);


    Card selectByPrimaryKey(String cardid);

    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardExample example);

    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);*/
}