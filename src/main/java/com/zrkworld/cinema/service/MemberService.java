package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.Member;
import com.zrkworld.cinema.pojo.MemberQuery;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public interface MemberService {


    /**
     * 接收前端请求条件，返回条件查询结果，并封装返回
     * @param query
     * @return 返回前端所需数据
     */
    CinemaResult getMemberData(MemberQuery query);

    /**
     * 会员注册
     * @param member
     * @return
     */
    CinemaResult memberRegister(Member member);

    /**
     * 删除一个会员
     * @param memberId
     * @return
     */
    CinemaResult delMember(String memberId);

    /**
     * 修改会员信息
     * @param member
     * @return
     */
    CinemaResult modifyMember(Member member);

    /**
     * 查询已注册会员数量
     * @return
     */
    Long getMemberNum();

    /**
     * 会员登录验证
     * @param member
     * @return
     */
    Member loginMember(Member member);

}
