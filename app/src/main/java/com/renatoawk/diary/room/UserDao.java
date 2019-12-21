package com.renatoawk.diary.room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.renatoawk.diary.model.User;

public abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(User user);

    @Delete
    abstract void delete(User user);

    @Query("Delete from _user")
    abstract void deleteAll();

    @Query("Select * from _user where __id=:id")
    abstract User get(int id);

    @Query("Select * from _user where __email=:email collate NOCASE")
    abstract User get(String email);

    @Query("Select * from _user where __email=:email collate NOCASE and __password=:password")
    abstract User login(String email, String password);

}
