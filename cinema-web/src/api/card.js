import request from '../utils/request'

    export default {
        //获取会员卡数据，请求参数pageIndex、pageSize
        getCardData(query){
            return request({
                url:"getCardData",
                method:'post',
                params:query
            });
        },
//注册会员卡，请求参数会员id，返回状态
registerCard(query){
    return request({
        url:"registerCard",
        method:'get',
        params:query
    });
},
//补卡，请求参数卡号id，返回新卡id号，并提示会员
reissueCard(query){
    return request({
        url:"reissueCard",
        method:'post',
        params:query
    });
},
//解挂会员卡，请求参数卡号，返回状态
cancelCard(query){
    return request({
        url:"cancelCard",
        method:'post',
        params:query
    });
},
//挂失会员卡，请求参数id，返回状态
loseCard(query){
    return request({
        url:"loseCard",
        method:'post',
        params:query
    });
},
//充值会员卡，请求参数金额、卡号、消费类型，返回状态
rechargeCard(query){
    return request({
        url:"rechargeCard",
        method:'post',
        params:query
    });
}
}