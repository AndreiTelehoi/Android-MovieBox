package com.example.movieboxproject.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movieboxproject.Data.Movie;

import java.util.List;

@Dao
public interface MoviesDAO {


    @Insert
    public void insertMovie(Movie movie);

    @Update
    public void updateMovie(Movie movie);

    @Delete
    public void deleteMovie(Movie movie);

    @Query("SELECT COUNT(*) FROM movies WHERE id = :id")
    public int checkID(int id);

    @Query("SELECT * FROM movies WHERE userID=:userID")
    public List<Movie> selectMovies(int userID);

    @Query("SELECT * FROM movies WHERE userID=:userID and year<2000")
    public List<Movie> selectOldMovies(int userID);


}
