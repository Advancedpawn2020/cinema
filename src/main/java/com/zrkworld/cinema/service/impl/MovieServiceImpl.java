package com.zrkworld.cinema.service.impl;

import com.zrkworld.cinema.mapper.MovieMapper;
import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.MemberQuery;
import com.zrkworld.cinema.pojo.Movie;
import com.zrkworld.cinema.pojo.PageResult;
import com.zrkworld.cinema.service.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    MovieMapper movieMapper;
    @Override
    public CinemaResult insertMovie(Movie movie) {
        movieMapper.insertMovie(movie);
        return CinemaResult.ok();
    }

    @Override
    public List<Movie> getMovieData() {
        return movieMapper.selectAllMovie();

    }

    @Override
    public void delMovie(String movieId) {
        movieMapper.deleteMovieById(movieId);
    }

    @Override
    public CinemaResult modifyMovie(Movie movie) {
        movieMapper.updateMovieById(movie);
        return CinemaResult.ok();
    }
}
