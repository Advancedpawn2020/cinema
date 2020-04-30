package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.Manager;

import java.util.List;


/**
 * @author zrk
 */
public interface ManagerService {
    /**
     * 接收managerId，返回Manager数据
     * @param managerId
     * @return Manager
     */
   Manager selectByManagerId(String managerId);
   Manager managerLogin(String managerId,String password);
}
