import request from '../utils/request'

export default {
    //判断管理员登录是否成功，参数为ID和密码，返回"success"或“error”
    managerLogin(){
        return request({
            url:'managerLogin',
            method:'get',
            params:query
        });
    },
//获取管理员数据，请求参数managerId
managerData(){
    return request({
        url:'getManagerData',
        method:'get',
        params:query
    });
},


}