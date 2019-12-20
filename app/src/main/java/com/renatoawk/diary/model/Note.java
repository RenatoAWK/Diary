package com.renatoawk.diary.model;

import com.renatoawk.diary.util.Constants;
import com.renatoawk.diary.util.Time;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {
    private long id;
    private String text;
    private int emotion;
    private Time created;
    private Time edited;

    public Note(long id, String text, int emotion, Time created, Time edited) {
        this.id = id;
        this.text = text;
        this.emotion = emotion;
        this.created = created;
        this.edited = edited;
    }

    public Note() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setId(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.NOTE_COLUMN_ID)){
            this.setId(jsonObject.getInt(Constants.NOTE_COLUMN_ID));
        }
    }

    public void setCreated(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.NOTE_COLUMN_CREATED)){
            this.setCreated(jsonObject.getString(Constants.NOTE_COLUMN_CREATED));
        }
    }

    private void setCreated(String value) {
        this.created = new Time();
        this.created.setTimeStamp(value);
    }

    private void setEdited(String value) {
        this.edited = new Time();
        this.edited.setTimeStamp(value);
    }

    public void setEdited(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.NOTE_COLUMN_EDITED)){
            this.setEdited(jsonObject.getString(Constants.NOTE_COLUMN_EDITED));
        }

    }

    public void setEmotion(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.NOTE_COLUMN_EMOTION)){
            this.setEmotion(jsonObject.getInt(Constants.NOTE_COLUMN_EMOTION));
        }

    }

    public void setText(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.NOTE_COLUMN_TEXT)){
            this.setText(jsonObject.getString(Constants.NOTE_COLUMN_TEXT));
        }
    }
}
