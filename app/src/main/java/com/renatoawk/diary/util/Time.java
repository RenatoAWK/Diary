package com.renatoawk.diary.util;

import java.util.Calendar;
import java.util.Locale;

public class Time {
    public Calendar calendar = Calendar.getInstance(Locale.getDefault());

    public void setHour(Integer value){this.calendar.set(Calendar.HOUR_OF_DAY, value);}
    public void setMinute(Integer value){this.calendar.set(Calendar.MINUTE, value);}
    public void setYear(Integer value){this.calendar.set(Calendar.YEAR, value);}
    public void setMonth(Integer value){this.calendar.set(Calendar.MONTH, value);}
    public void setDayOfMonth(Integer value){this.calendar.set(Calendar.DAY_OF_MONTH, value);}

    public Integer getHour(){return this.calendar.get(Calendar.HOUR_OF_DAY);}
    public Integer getMinute(){ return this.calendar.get(Calendar.MINUTE);}
    public Integer getSecound(){ return 0; }
    public Integer getYear(){return this.calendar.get(Calendar.YEAR);}
    public Integer getMonth(){return this.calendar.get(Calendar.MONTH);}
    public Integer getDayOfMonth(){return this.calendar.get(Calendar.DAY_OF_MONTH);}

    public Time(Integer hour, Integer minute) {
        this.setHour(hour);
        this.setMinute(minute);
    }

    public Time(Integer dayOfMonth, Integer month, Integer year, Integer hour, Integer minute) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setYear(year);
        this.setMonth(month);
        this.setDayOfMonth(dayOfMonth);
    }

    public Time(Calendar calendar){
        this.calendar = calendar;
    }

    public Time(){}

    public void setFormatedTime(String value) {
        String[]valueS = value.split(":");
        this.setHour(Integer.parseInt(valueS[0]));
        this.setMinute(Integer.parseInt(valueS[1]));
    }


    public String getFormatedTime(){
        return this.getHour() +":"+
                this.getMinute();
    }

    public String getFormatedDate(){
        return this.getDayOfMonth() +"/"+
                this.getMonth() +"/"+
                this.getYear();
    }

    public String getTimePostgres(){
        return getFormatedTime();
    }

    public String getFormatedDateTime(){
        return getFormatedDate()+" "+getFormatedTime();
    }

    public String getTimeStampPostgres(){
        return this.getYear() +"-"+
                this.getMonth() +"-"+
                this.getDayOfMonth()+" "+ getFormatedTime()+":"+this.getSecound();

    }
}