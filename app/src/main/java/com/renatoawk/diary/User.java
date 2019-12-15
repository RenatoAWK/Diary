package com.renatoawk.diary;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String name;
    private String password;
    private String email;
    private Integer id;
    private boolean notify;
    private Time time;
    private Integer theme;

    public User(){}

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(String name, String password, String email, Integer id, boolean notify, Time time, Integer theme) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.notify = notify;
        this.time = time;
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time value) {
        this.time = value;
    }

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }

    public void setTime(String value) {
        this.time = new Time();
        this.time.setFormatedTime(value);
    }


    public void setName(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.USER_COLUMN_NAME)){
            this.setName(jsonObject.getString(Constants.USER_COLUMN_NAME));
        }
    }

    public void setID(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.USER_COLUMN_ID)){
            this.setId(jsonObject.getInt(Constants.USER_COLUMN_ID));
        }
    }

    public void setEmail(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.USER_COLUMN_EMAIL)){
            this.setEmail(jsonObject.getString(Constants.USER_COLUMN_EMAIL));
        }
    }

    public void setPassword(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.USER_COLUMN_PASSWORD)){
            this.setPassword(jsonObject.getString(Constants.USER_COLUMN_PASSWORD));
        }
    }

    public void setNotify(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.USER_COLUMN_NOTIFY)){
            this.setNotify(jsonObject.getBoolean(Constants.USER_COLUMN_NOTIFY));
        }
    }

    public void setTime(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.USER_COLUMN_TIME)){
            this.setTime(jsonObject.getString(Constants.USER_COLUMN_TIME));
        }
    }

    public void setTheme(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull(Constants.USER_COLUMN_THEME)){
            this.setTheme(jsonObject.getInt(Constants.USER_COLUMN_THEME));
        }
    }
}
