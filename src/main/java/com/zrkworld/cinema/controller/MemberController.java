package com.zrkworld.cinema.controller;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.Member;
import com.zrkworld.cinema.pojo.MemberQuery;
import com.zrkworld.cinema.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
@CrossOrigin
@RestController
public class MemberController {
    @Resource
    MemberService memberService;

    /**
     * //查询会员列表，参数pageIndex、pageSize、name、birtydayQuery，
     * // 返回key为list 的数组和key为pageTotal的整型，birtydayQuery:空表示无限制，0示当天过生日，7表示7天内过生日
     *
     * export const memberData = query=>{
     *     return request({
     *         url:'memberData',
     *         method:'get',
     *         params:query
     *     });
     * };
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param birthdayQuery
     * @return
     */
    @RequestMapping("memberData")
    public CinemaResult getMemberData(String pageIndex, String pageSize, String name, String birthdayQuery){
        MemberQuery query = new MemberQuery();
        if(StringUtils.isNotBlank(pageIndex)){
            query.setPageIndex(Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
        }
        if(StringUtils.isNotBlank(name)){
            query.setName(name);
        }
        if(StringUtils.isNotBlank(birthdayQuery)){
            query.setBirthdayQuery(birthdayQuery);
        }
        return memberService.getMemberData(query);
    }

    @RequestMapping("memberRegister")
    public CinemaResult memberRegister(Member member){
        return memberService.memberRegister(member);
    }
    /**
     * //会员删除，参数MemberId，返回Result
     * export const delMember = query=>{
     *     return request({
     *         url:'delMember',
     *         method:'get',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("delMember")
    public CinemaResult delMember(Member member){
        System.out.println(member);
        return memberService.delMember(member.getmemberId());
    }
    /**
     * //修改会员信息，参数Member，返回Result
     * export const modifyMember = query=>{
     *     return request({
     *         url:'modifyMember',
     *         method:'get',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("modifyMember")
    public CinemaResult modifyMember(Member member){
        return memberService.modifyMember(member);
    }

    /**
     * //会员登录，参数memberId，返回200
     * export const loginMember = query=>{
     *     return request({
     *         url:'loginMember',
     *         method:'get',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("loginMember")
    public CinemaResult loginMember(Member member){
        if(memberService.loginMember(member)!=null){
            return CinemaResult.ok();
        }else{
            return CinemaResult.build(500,"无此账号");
        }
    }

}
