package com.zrkworld.cinema.controller;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.Manager;
import com.zrkworld.cinema.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zrk
 */
@RestController
public class ManagerController {
    @Resource
    ManagerService managerService;
    @RequestMapping("getManagerData")
    public CinemaResult getManagerData(String managerId){
        return CinemaResult.ok(managerService.selectByManagerId(managerId));
    }

    @RequestMapping("managerLogin")
    public CinemaResult managerLogin(String managerId, String password){
        if (managerService.managerLogin(managerId, password) == null) {
            return CinemaResult.build(500,"error");
        } else {
            return CinemaResult.ok();
        }
    }
}
/*
//获取管理员数据，请求参数managerId
export const managerData = query=>{
    return request({
        url:'getManagerData',
        method:'get',
        params:query
    });
};



* */