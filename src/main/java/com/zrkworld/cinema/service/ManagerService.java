package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.Manager;

import java.util.List;



/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public interface ManagerService {
    /**
     * 接收managerId，返回Manager数据
     * @param managerId
     * @return Manager
     */
   Manager selectByManagerId(String managerId);

    /**
     * 管理员登录验证
     * @param managerId
     * @param password
     * @return
     */
   Manager managerLogin(String managerId,String password);
}
