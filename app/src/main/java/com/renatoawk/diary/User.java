package com.renatoawk.diary;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

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

    public void setHour(String value) {
        this.time = new Time(value);
    }

    public void setHour(Time time){
        this.time = time;
    }

    public void setName(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("__name")){
            this.setName(jsonObject.getString("__name"));
        }
    }

    public void setID(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("__id")){
            this.setId(jsonObject.getInt("__id"));
        }
    }

    public void setEmail(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("__email")){
            this.setEmail(jsonObject.getString("__email"));
        }
    }

    public void setPassword(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("__password")){
            this.setPassword(jsonObject.getString("__password"));
        }
    }

    public void setNotify(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("__notify")){
            this.setNotify(jsonObject.getBoolean("__notify"));
        }
    }

    public void setTime(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("__hour")){
            this.setHour(jsonObject.getString("__hour"));
        }
    }

    public void setTheme(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("__theme")){
            this.setTheme(jsonObject.getInt("__theme"));
        }
    }
}
