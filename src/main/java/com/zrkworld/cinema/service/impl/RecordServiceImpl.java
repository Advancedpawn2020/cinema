package com.zrkworld.cinema.service.impl;

import com.zrkworld.cinema.mapper.RecordMapper;
import com.zrkworld.cinema.pojo.Record;
import com.zrkworld.cinema.pojo.Schart1Data;
import com.zrkworld.cinema.service.RecordService;
import com.zrkworld.cinema.utils.DateManageSystem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
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

    @Override
    public Long getMovieNum() {
        return recordMapper.selectMovieCount();
    }
    @Override
    public Long getConsumeCount() {
        return recordMapper.selectConsumeCount();
    }

    @Override
    public Long getIntegralExchangeCount() {
        return recordMapper.selectIntegralExchangeCount();
    }

    @Override
    public Long getRechargeCount() {
        return recordMapper.selectRechargeCount();
    }

    @Override
    public Schart1Data getSchart1Data() {
        Schart1Data schart1Data = new Schart1Data();
        Integer[] consume = new Integer[5];
        Integer[] recharge = new Integer[5];
        Integer[] integral = new Integer[5];
        //1.获得上一个周的周一到周五的日期
        DateManageSystem dateManageSystem = new DateManageSystem();
        String[] DateLimit = dateManageSystem.getLastWeek();

        //2.循环上一个周一到周五，查询在当日0点到24点之间交易的记录,并计算相对应的数据之和
        for(int i = 0;i<5;i++){
            String startDate = DateLimit[i];
            String endDate = DateLimit[i+1];
            //消费
            consume[i] = recordMapper.selectConsumeCountByDate(startDate,endDate)==null?0:recordMapper.selectConsumeCountByDate(startDate,endDate);
            consume[i]*=-1;
            //充值
            recharge[i] = recordMapper.selectrechargeCountByDate(startDate,endDate)==null?0:recordMapper.selectrechargeCountByDate(startDate,endDate);
            //积分兑换
            integral[i] = recordMapper.selectintegralCountByDate(startDate,endDate)==null?0:recordMapper.selectintegralCountByDate(startDate,endDate);
            integral[i]*=-1;
        }
        //3.存入Schart1Data中
        schart1Data.setConsume(consume);
        schart1Data.setRecharge(recharge);
        schart1Data.setIntegral(integral);
        return schart1Data;
    }
}
