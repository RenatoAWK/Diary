package com.renatoawk.diary;

public class Time {
    private Integer hour, minute, secound;

    public Time(Integer hour, Integer minute, Integer secound) {
        this.hour = hour;
        this.minute = minute;
        this.secound = secound;
    }

    public Time(String value){
        setFormated(value);
    }

    private void setFormated(String value) {
        String[]valueS = value.split(":");
        this.hour = Integer.parseInt(valueS[0]);
        this.minute = Integer.parseInt(valueS[1]);
        this.secound = Integer.parseInt(valueS[2]);
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecound() {
        return secound;
    }

    public void setSecound(Integer secound) {
        this.secound = secound;
    }

    public String getFormated(){
        return this.hour +":"+ this.minute +":"+ this.secound;
    }
}
