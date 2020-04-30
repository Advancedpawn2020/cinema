package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.Member;
import java.util.List;

import com.zrkworld.cinema.pojo.MemberQuery;
import org.apache.ibatis.annotations.Param;

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

    void updateMember(Member member);

    Member selectMemberByLogin(Member member);
    /*int countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(String username);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);*/
}