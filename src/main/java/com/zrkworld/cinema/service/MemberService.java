package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.Member;
import com.zrkworld.cinema.pojo.MemberQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zrk
 */
public interface MemberService {


    /**
     * 接收前端请求条件，返回条件查询结果，并封装返回
     * @param query
     * @return 返回前端所需数据
     */
    CinemaResult getMemberData(MemberQuery query);
    CinemaResult memberRegister(Member member);

    /**
     * 删除一个会员
     * @param memberId
     * @return
     */
    CinemaResult delMember(String memberId);

    CinemaResult modifyMember(Member member);

    Long getMemberNum();

    Member loginMember(Member member);
}
