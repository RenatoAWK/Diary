package com.renatoawk.diary.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.renatoawk.diary.room.Repository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Note>> notes;

    public NoteViewModel(Application application){
        super(application);
        repository = new Repository(application);
        notes = repository.getNotesByIdUser();
    }

    public LiveData<List<Note>> getNotes(){
        return notes;
    }
}
