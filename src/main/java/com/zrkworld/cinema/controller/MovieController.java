package com.zrkworld.cinema.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkworld.cinema.pojo.*;
import com.zrkworld.cinema.service.MovieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
@CrossOrigin
@RestController
public class MovieController {
    @Resource
    MovieService movieService;
    /**
     * //添加电影票数据，需要返回电影票全部数据，因为电影票id需要服务端生成
     * export const insertMovie = query=>{
     *     return request({
     *         url:'insertMovie',
     *         method:'post',
     *         params:query
     *     })
     * };
     */
    @RequestMapping("insertMovie")
    public CinemaResult insertMovie(Movie movie){
        return movieService.insertMovie(movie);

    }
    /**
     *  //获取电影票数据，请求参数：pageIndex，pageSize，返回电影列表和总记录数
     * export const movieData = query =>{
     *     return request({
     *         url:'getMovie',
     *         method:'get',
     *         params:query
     *     });
     * };
     */
    @RequestMapping("getMovie")
    public CinemaResult getMovie(String pageIndex,String pageSize){
        //这里使用分页插件pagehelper
        PageResult pageResult = new PageResult();
        PageHelper.startPage(Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
        List lists = movieService.getMovieData();
        PageInfo<Card> pageInfo = new PageInfo<>(lists);
        pageResult.setList(pageInfo.getList());
        pageResult.setPageTotal(pageInfo.getTotal());
        return CinemaResult.ok(pageResult);
    }
    /**
     * //删除电影数据，请求参数：movieId
     * export const delMovie = query=>{
     *     return request({
     *         url:'delMovie',
     *         method:'get',
     *         params:query
     *     });
     * };

     */
    @RequestMapping("delMovie")
    public CinemaResult delMovie(String movieId){
        movieService.delMovie(movieId);
        return CinemaResult.ok();
    }
    /**
     *      * //修改电影数据，请求参数:movieId、name、price、time
     *      * export const modifyMovie = query=>{
     *      *     return request({
     *      *         url:'modifyMovie',
     *      *         method:'get',
     *      *         params:query
     *      *     });
     *      * };
     */
    @RequestMapping("modifyMovie")
    public CinemaResult modifyMovie(Movie movie){
        return movieService.modifyMovie(movie);
    }
}
/*
*
* //删除电影数据，请求参数：movieId
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
};*/