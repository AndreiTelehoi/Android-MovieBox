package com.example.movieboxproject.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movieboxproject.Data.User;

@Dao
public interface UsersDAO {

    @Insert
    public void insertUser(User user);

    @Delete
    public void deleteUser(User user);

    @Update
    public void updateUser(User user);

    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    public int checkUsername(String username);

    @Query("select COUNT(*) from users where username=:searchedUsername and password=:searchedPassword;")
    public int userExists(String searchedUsername, String searchedPassword);

    @Query("SELECT * FROM users WHERE username = :username")
    public User getUser(String username);




}
