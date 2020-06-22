package com.zrkworld.cinema.controller;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.DealData;
import com.zrkworld.cinema.pojo.StatisticData;
import com.zrkworld.cinema.service.CardService;
import com.zrkworld.cinema.service.MemberService;
import com.zrkworld.cinema.service.MovieService;
import com.zrkworld.cinema.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@CrossOrigin
@RestController
public class StatisticController {
    @Resource
    MemberService memberService;
    @Resource
    CardService cardService;
    @Resource
    RecordService recordService;
/**
 * //获取统计数字数据，请求参数无
 * export const statisticData = query=>{
 *     return request({
 *         url:'getStatisticData',
 *         method:'get',
 *         params:query
 *     });
 * };
 */
    @RequestMapping("getStatisticData")
    public CinemaResult getStatisticData(){
        StatisticData s = new StatisticData();
        s.setMemberNum(memberService.getMemberNum());
        s.setCardNum(cardService.getCardNum());
        s.setMovieNum(recordService.getMovieNum());
        return CinemaResult.ok(s);
    }
    /**
     *  //获取消费充值等金额数据，请求参数无
     * export const requestDealData = query=>{
     *     return request({
     *         url:'requestDealData',
     *         method:'get',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("requestDealData")
    public CinemaResult requestDealData(){
        DealData dealData = new DealData();
        dealData.setConsume(recordService.getConsumeCount()*-1);
        dealData.setIntegral(recordService.getIntegralExchangeCount()*-1);
        dealData.setRecharge(recordService.getRechargeCount());
        return CinemaResult.ok(dealData);
    }
    /**
     *  //获取充值消费数据的图标数据，请求参数无，返回包含三个整型数组的对象
     * export const getSchart1Data = query=>{
     *     return request({
     *         url:"getSchart1Data",
     *         method:'post',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("getSchart1Data")
    public CinemaResult getSchart1Data(){
        return CinemaResult.ok(recordService.getSchart1Data());
    }
}
