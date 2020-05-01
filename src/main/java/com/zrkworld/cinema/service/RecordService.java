package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.Record;
import com.zrkworld.cinema.pojo.Schart1Data;

import java.util.List;

public interface RecordService {
    /**
     * 通过memberId查到所拥有的的所有cardId,然后查询到所有cardId的交易记录
     * @param memberId
     * @return
     */
    List<Record> selectRecordByMemberId(String memberId);

    /**
     * 删除会员卡的消费记录
     * @param cardId
     */
    void deleteRecordByCardId(String cardId);

    /**
     * 新增消费记录
     * @param record
     */
    void insertRecord(Record record);

    /**
     * 获取所有电影票售出数量
     * @return
     */
    Long getMovieNum();

    /**
     * 获取消费额总数
     * @return
     */
    Long getConsumeCount();

    /**
     * 获取积分兑换总数
     * @return
     */
    Long getIntegralExchangeCount();

    /**
     * 获取充值总数
     * @return
     */
    Long getRechargeCount();

    /**
     * 获取统计图表所需数据
     * @return
     */
    Schart1Data getSchart1Data();
}
