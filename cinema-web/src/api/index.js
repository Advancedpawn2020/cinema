import request from '../utils/request';
//所有的向api的请求在这里写
//fetchData是以query为参数的函数，将请求参数作为参数，返回请求api的结果
export const fetchData = query => {
    return request({
        url: './table.json',
        method: 'get',
        //这里是调用地传递过来的参数列表
        params: query
    });
};
//判断管理员登录是否成功，参数为ID和密码，返回"success"或“error”
export const managerLogin = query =>{
    return request({
        url:'managerLogin',
        method:'get',
        params:query
    });
};
//获取管理员数据，请求参数managerId
export const managerData = query=>{
    return request({
        url:'getManagerData',
        method:'get',
        params:query
    });
};
//获取电影票数据，请求参数：pageIndex，pageSize，返回电影列表和总记录数
export const movieData = query =>{
    return request({
        url:'getMovie',
        method:'get',
        params:query
    });
};
//获取交易数据，请求参数无
export const dealData = query=>{
    return request({
        url:'getDealData',
        method:'get',
        params:query
    });
};

//添加电影票数据
export const insertMovie = query=>{
    return request({
        url:'insertMovie',
        method:'post',
        params:query
    })
};
//删除电影数据，请求参数：movieId
export const delMovie = query=>{
    return request({
        url:'delMovie',
        method:'get',
        params:query
    });
};
//修改电影数据，请求参数:movieId、name、price、time
export const modifyMovie = query=>{
    return request({
        url:'modifyMovie',
        method:'get',
        params:query
    });
};
//查询会员列表，参数pageIndex、pageSize、name、birtydayQuery，
// 返回key为list 的数组和key为pageTotal的整型，birtydayQuery:空表示无限制，0示当天过生日，7表示7天内过生日

export const memberData = query=>{
    return request({
        url:'memberData',
        method:'get',
        params:query
    });
};
//会员注册，参数Member，返回Result，会员id在服务端用redis生成随机字符串
export const registerMember = query=>{

    return request({
        url:'memberRegister',
        method:'get',
        params:query
    });
};
//会员删除，参数MemberId，返回Result
//注，返回值不能直接是一个数值，必须是键值对的模式
export const delMember = query=>{
    return request({
        url:'delMember',
        method:'get',
        params:query
    });
};
//修改会员信息，参数Member，返回Result
export const modifyMember = query=>{
    return request({
        url:'modifyMember',
        method:'get',
        params:query
    });
};
//会员登录，参数memberId、password，返回200
export const loginMember = query=>{
    return request({
        url:'loginMember',
        method:'get',
        params:query
    });
};
//获取统计数字数据，请求参数无
export const statisticData = query=>{
    return request({
        url:'getStatisticData',
        method:'get',
        params:query
    });
};
//获取消费充值等金额数据，请求参数无
export const requestDealData = query=>{
    return request({
        url:'requestDealData',
        method:'get',
        params:query
    });
};
//获取会员卡数据，请求参数pageIndex、pageSize
export const getCardData = query=>{
    return request({
        url:"getCardData",
        method:'post',
        params:query
    });
};
//注册会员卡，请求参数会员id，返回状态
export const registerCard = query=>{
    return request({
        url:"registerCard",
        method:'get',
        params:query
    });
};
//补卡，请求参数卡号id，返回新卡id号，并提示会员
export const reissueCard = query=>{
    return request({
        url:"reissueCard",
        method:'post',
        params:query
    });
};
//解挂会员卡，请求参数卡号，返回状态
export const cancelCard = query=>{
    return request({
        url:"cancelCard",
        method:'post',
        params:query
    });
};
//挂失会员卡，请求参数id，返回状态
export const loseCard = query=>{
    return request({
        url:"loseCard",
        method:'post',
        params:query
    });
};
//充值会员卡，请求参数金额、卡号、消费类型，返回状态
export const rechargeCard = query=>{
    return request({
        url:"rechargeCard",
        method:'post',
        params:query
    });
};
//消费，请求参数金额、卡号、消费类型，返回状态
export const consumeCard = query=>{
    return request({
        url:"consumeCard",
        method:'post',
        params:query
    });
};
//交易记录查询，请求参数会员id，pageIndex,pageSize
export const queryRecord = query=>{
    return request({
        url:"queryRecord",
        method:'post',
        params:query
    })
};
//积分兑换，请求参数：会员id，积分
//由于是总积分兑换，所以需要将消耗积分分散到所有卡上面
export const exchangeIntegral = query=>{
    return request({
        url:"exchangeIntegral",
        method:'post',
        params:query
    });
};
//通过模糊条件查询到memberId,CardId，请求参数CardId，返回CardId列表
export const getCardIdByFuzzyQuery = query=>{
    return request({
        url:"getCardIdByFuzzyQuery",
        method:'post',
        params:query
    });
};
//通过完整cardid来查询card数据，返回Card对象
export const getCardByCardId = query=>{
    return request({
        url:"getCardByCardId",
        method:'post',
        params:query
    });
};
//获取充值消费数据的图标数据，请求参数无，返回包含三个整型数组的对象
export const getSchart1Data = query=>{
    return request({
        url:"getSchart1Data",
        method:'post',
        params:query
    });
};
//获取业务增长的图标数据，请求参数无，返回包含三个整型数组的对象：memberNum,cardNum,movieNum
export const getSchart2Data = query=>{
    return request({
        url:"getSchart2Data",
        method:'post',
        params:query
    });
};
//获取比例的图标数据，请求参数无，返回三者的比例对象
export const getSchart3Data = query=>{
    return request({
        url:"getSchart3Data",
        method:'post',
        params:query
    });
};
