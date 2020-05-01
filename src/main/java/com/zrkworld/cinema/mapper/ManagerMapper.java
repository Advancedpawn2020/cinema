package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.Manager;


/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
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

}