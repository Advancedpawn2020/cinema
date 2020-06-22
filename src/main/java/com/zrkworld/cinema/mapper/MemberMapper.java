package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.Member;
import java.util.List;

import com.zrkworld.cinema.pojo.MemberQuery;
import org.apache.ibatis.annotations.Param;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public interface MemberMapper {
    /**
     * 以检索规则进行查找
     * @param query
     * @return 符合规则的元组
     */
    List<Member> selectByOrder(MemberQuery query);

    /**
     * 以检索规则进行查找，返回条数
     * @return 当前检索规则下的条数
     */
    Long selectCountByOrder(MemberQuery query);

    /**
     * 添加会员项至会员表
     * @param member
     */
    void insertMember(Member member);

    /**
     * 删除会员表一项
     * @param memberId
     * @return
     */
    void deleteMember(String memberId);

    /**
     * 根据会员id更新会员信息
     * @param member
     */
    void updateMember(Member member);

    /**
     * 根据会员id查看
     * @param member
     * @return
     */
    Member selectMemberByLogin(Member member);

}