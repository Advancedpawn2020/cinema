package com.zrkworld.cinema.service;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.MemberQuery;
import com.zrkworld.cinema.pojo.Movie;

import java.util.List;

public interface MovieService {
    CinemaResult insertMovie(Movie movie);

    List<Movie> getMovieData();

    void delMovie(String movieId);

    CinemaResult modifyMovie(Movie movie);
}
