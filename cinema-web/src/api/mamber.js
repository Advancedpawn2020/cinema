import request from '../utils/request'

export default {
    //查询会员列表，参数pageIndex、pageSize、name、birtydayQuery，
// 返回key为list 的数组和key为pageTotal的整型，birtydayQuery:空表示无限制，0示当天过生日，7表示7天内过生日

    memberData(query){
        return request({
            url:'memberData',
            method:'get',
            params:query
        });
    },
//会员注册，参数Member，返回Result，会员id在服务端用redis生成随机字符串
registerMember(query){

    return request({
        url:'memberRegister',
        method:'get',
        params:query
    });
},
//会员删除，参数MemberId，返回Result
//注，返回值不能直接是一个数值，必须是键值对的模式
delMember(query){
    return request({
        url:'delMember',
        method:'get',
        params:query
    });
},
//修改会员信息，参数Member，返回Result
modifyMember(query){
    return request({
        url:'modifyMember',
        method:'get',
        params:query
    });
},
//会员登录，参数memberId，返回200
loginMember(query){
    return request({
        url:'loginMember',
        method:'get',
        params:query
    });
},
}