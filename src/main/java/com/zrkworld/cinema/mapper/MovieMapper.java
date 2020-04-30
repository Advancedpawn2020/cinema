package com.zrkworld.cinema.mapper;

import com.zrkworld.cinema.pojo.CinemaResult;
import com.zrkworld.cinema.pojo.MemberQuery;
import com.zrkworld.cinema.pojo.Movie;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MovieMapper {
    /**
     * 需求：使用MyBatis往MySQL数据库中插入一条记录后，需要返回该条记录的自增主键值。
     * 方法：在定义xml映射器时设置属性useGeneratedKeys值为true，并分别指定属性keyProperty和keyColumn为对应的数据库记录主键字段与Java对象的主键属性。
     * 由于需要返回的还有日期，所以这里就不返回主键了
     * @param movie
     */
    void insertMovie(Movie movie);

    /**
     * 取出来的时间要进行date_format，否则会取出来.0的时间数据
     * @param
     * @return
     */
    List<Movie> selectAllMovie();

    void deleteMovieById(String movieId);

    void updateMovieById(Movie movie);
  /*  int countByExample(MovieExample example);

    int deleteByExample(MovieExample example);

    int deleteByPrimaryKey(String movieid);

    int insert(Movie record);

    int insertSelective(Movie record);

    List<Movie> selectByExample(MovieExample example);

    Movie selectByPrimaryKey(String movieid);

    int updateByExampleSelective(@Param("record") Movie record, @Param("example") MovieExample example);

    int updateByExample(@Param("record") Movie record, @Param("example") MovieExample example);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);*/
}