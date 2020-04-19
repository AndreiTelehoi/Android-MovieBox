package com.example.movieboxproject.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.movieboxproject.Data.Movie;
import com.example.movieboxproject.Data.User;

@Database(entities =  {User.class, Movie.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract UsersDAO usersDAO();
    public abstract MoviesDAO moviesDAO();
}
