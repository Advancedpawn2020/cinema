package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.Record;

import java.util.List;

public interface RecordService {
    /**
     * 通过memberId查到所拥有的的所有cardId,然后查询到所有cardId的交易记录
     * @param memberId
     * @return
     */
    List<Record> selectRecordByMemberId(String memberId);

    void deleteRecordByCardId(String cardId);
    void insertRecord(Record record);
}
