package com.zrkworld.cinema.service.impl;

import com.zrkworld.cinema.mapper.RecordMapper;
import com.zrkworld.cinema.pojo.Record;
import com.zrkworld.cinema.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    RecordMapper recordMapper;

    @Override
    public List<Record> selectRecordByMemberId(String memberId) {
        return recordMapper.selectRecordByMemberId(memberId);
    }

    @Override
    public void deleteRecordByCardId(String cardId) {
        recordMapper.deleteRecordByCardId(cardId);
    }

    @Override
    public void insertRecord(Record record) {
        recordMapper.insertRecord(record);
    }
}
