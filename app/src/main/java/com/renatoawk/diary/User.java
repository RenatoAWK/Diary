package com.renatoawk.diary;

import java.util.Calendar;

public class User {
    private String name;
    private String password;
    private String email;
    private Integer id;
    private boolean notify;
    private Calendar hour;
    private Integer theme;

    public User(){}

    public User(String name, String password, String email, Integer id, boolean notify, Calendar hour, Integer theme) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.notify = notify;
        this.hour = hour;
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

    public Calendar getHour() {
        return hour;
    }

    public void setHour(Calendar hour) {
        this.hour = hour;
    }

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }

    public void setHour(String hour) {

    }
}
