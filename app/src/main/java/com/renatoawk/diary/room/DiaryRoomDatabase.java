package com.renatoawk.diary.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.renatoawk.diary.model.Note;
import com.renatoawk.diary.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Note.class}, version = 1, exportSchema = false)
public abstract class DiaryRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile DiaryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DiaryRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (DiaryRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DiaryRoomDatabase.class, "diary_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
