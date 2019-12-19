package com.renatoawk.diary.model;

import com.renatoawk.diary.util.Time;

public class Note {
    private long id_user;
    private String text;
    private int emotion;
    private Time created;
    private Time edited;

    public Note(long id_user, String text, int emotion, Time created, Time edited) {
        this.id_user = id_user;
        this.text = text;
        this.emotion = emotion;
        this.created = created;
        this.edited = edited;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getEmotion() {
        return emotion;
    }

    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }

    public Time getCreated() {
        return created;
    }

    public void setCreated(Time created) {
        this.created = created;
    }

    public Time getEdited() {
        return edited;
    }

    public void setEdited(Time edited) {
        this.edited = edited;
    }
}
