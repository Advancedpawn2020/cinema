package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.MemberQuery;
import com.zrkworld.cinema.pojo.Movie;

import java.util.List;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public interface MovieService {
    /**
     * 新增电影
     * @param movie
     * @return
     */
    CinemaResult insertMovie(Movie movie);

    /**
     * 获取所有电影信息
     * @return
     */
    List<Movie> getMovieData();

    /**
     * 删除电影信息
     * @param movieId
     */
    void delMovie(String movieId);

    /**
     * 修改电影信息
     * @param movie
     * @return
     */
    CinemaResult modifyMovie(Movie movie);

}
