package com.renatoawk.diary.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.renatoawk.diary.util.Constants;
import com.renatoawk.diary.util.Time;

import org.json.JSONException;
import org.json.JSONObject;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "_note")
public class Note implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "__id")
    private long id;

    @ColumnInfo(name = "__text")
    private String text;

    @ColumnInfo(name = "__emotion")
    private int emotion;

    @ColumnInfo(name = "__created")
    private Time created;

    @ColumnInfo(name = "__edited")
    private Time edited;

    @ForeignKey(entity = User.class, parentColumns = "__id", childColumns = "__id_user", onDelete = CASCADE, onUpdate = CASCADE)
    @ColumnInfo(name = "__id_user")
    private long id_user;

    public Note(long id, long id_user, String text, int emotion, Time created, Time edited) {
        this.id = id;
        this.id_user = id_user;
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

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setId_user(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.NOTE_COLUMN_ID_USER)){
            this.setId_user(jsonObject.getInt(Constants.NOTE_COLUMN_ID_USER));
        }
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.text);
        dest.writeInt(this.emotion);
        dest.writeParcelable(this.created, flags);
        dest.writeParcelable(this.edited, flags);
        dest.writeLong(this.id_user);
    }

    public Note(Parcel in) {
        this.id = in.readLong();
        this.text = in.readString();
        this.emotion = in.readInt();
        this.created = in.readParcelable(Time.class.getClassLoader());
        this.edited = in.readParcelable(Time.class.getClassLoader());
        this.id_user = in.readLong();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj != null & obj instanceof Note){
            return ((Note) obj).getId() == this.getId();
        }
        return false;
    }

    public boolean isGreaterThan(Note note){
        return this.getEdited().calendar.compareTo(note.getEdited().calendar) > 0;
    }
}
