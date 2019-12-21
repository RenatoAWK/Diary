package com.renatoawk.diary.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.renatoawk.diary.model.Note;

import java.util.List;

public abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(Note note);

    @Delete
    abstract void delete(Note note);

    @Query("Delete from _note")
    abstract void deleteAll();

    @Query("Select * from _note where __id=:id")
    abstract Note get(int id);

    @Query("Select * from _note where __id_user=:id_user")
    abstract LiveData<List<Note>> getByIdUser(int id_user);

}
