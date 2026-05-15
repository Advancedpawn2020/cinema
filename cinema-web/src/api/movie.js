import request from '../utils/request'

export default {
    //获取电影票数据，请求参数：pageIndex，pageSize，返回电影列表和总记录数
    movieData(query){
        return request({
            url:'getMovie',
            method:'get',
            params:query
        });
    },
}