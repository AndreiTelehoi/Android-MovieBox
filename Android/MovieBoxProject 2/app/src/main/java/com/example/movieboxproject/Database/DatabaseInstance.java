package com.example.movieboxproject.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseInstance {
    private static DatabaseInstance instance;
    private RoomDB roomDB;

    private DatabaseInstance(Context context) {
        roomDB = Room.databaseBuilder(context, RoomDB.class, "database").allowMainThreadQueries().build();
    }

    public static DatabaseInstance getInstance(Context context){
        if(instance==null){
            instance=new DatabaseInstance(context);
        }
        return instance;
    }

    public UsersDAO getUsersDAO() {
        return roomDB.usersDAO();
    }

    public MoviesDAO getMoviesDAO(){
        return roomDB.moviesDAO();
    }
}
