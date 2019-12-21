package com.renatoawk.diary.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.renatoawk.diary.model.Note;
import com.renatoawk.diary.model.Session;
import com.renatoawk.diary.model.User;

import java.util.List;

public class Repository {

    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public Repository(Application application){
        DiaryRoomDatabase db = DiaryRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
        notes = noteDao.getByIdUser(Session.user.getId());
    }

    public void insertNote(Note note){
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> noteDao.insert(note));
    }

    public void deleteNote(Note note){
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> noteDao.delete(note));
    }

    public void deleteAllNote(){
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> noteDao.deleteAll());
    }

    public Note getNote(int id){
        return noteDao.get(id);
    }

    public LiveData<List<Note>> getNotesByIdUser(){
        return notes;
    }

}
