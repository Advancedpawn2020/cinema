package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.Manager;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author zrk
 */
public interface ManagerMapper {
    /**
     * 接收managerId，返回Manager
     *
     * @param managerId
     * @return Manager
     */
    Manager selectByManagerId(String managerId);

    /**
     *
     * @param managerId
     * @param password
     * @return
     */
    Manager managerLogin(String managerId,String password);

    void updateManagerTime(String managerId);

/*    int countByExample(ManagerExample example);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(String managerid);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(String managerid);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);*/
}