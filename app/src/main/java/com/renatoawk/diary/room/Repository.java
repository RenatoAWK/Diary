package com.renatoawk.diary.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.renatoawk.diary.model.Note;
import com.renatoawk.diary.model.Session;
import com.renatoawk.diary.model.User;

import java.util.List;

public class Repository {

    private UserDao userDao;
    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public Repository(Application application){
        DiaryRoomDatabase db = DiaryRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        noteDao = db.noteDao();
        notes = noteDao.getByIdUser(Session.user.getId());
    }

    public void insertUser(User user){
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public void deleteUser(User user){
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> userDao.delete(user));
    }

    public void deleteAllUser(){
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> userDao.deleteAll());
    }

    public User getUser(int id){
        return userDao.get(id);
    }

    public User getUser(String email){
        return userDao.get(email);
    }

    public User login(String email, String password){
        return userDao.login(email, password);
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
