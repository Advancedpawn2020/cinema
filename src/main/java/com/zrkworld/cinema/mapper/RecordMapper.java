package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.Record;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecordMapper {
    void deleteRecordByCardId(String cardId);

    void insertRecord(Record record);

    List<Record> selectRecordByMemberId(String memberId);
   /* int countByExample(RecordExample example);

    int deleteByExample(RecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(Record record);

    int insertSelective(Record record);

    List<Record> selectByExample(RecordExample example);

    Record selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Record record, @Param("example") RecordExample example);

    int updateByExample(@Param("record") Record record, @Param("example") RecordExample example);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);*/
}