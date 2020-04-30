package com.zrkworld.cinema.controller;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.Member;
import com.zrkworld.cinema.pojo.MemberQuery;
import com.zrkworld.cinema.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zrk
 */
@RestController
public class MemberController {
    @Resource
    MemberService memberService;
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
     * //会员登录，参数memberId、password，返回200
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
            return CinemaResult.build(500,"账号或者密码错误");
        }
    }

}
