package com.zrkworld.cinema.controller;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.StatisticData;
import com.zrkworld.cinema.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {
    @Autowired
    MemberService memberService;

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
        return CinemaResult.ok(s);
    }
}
