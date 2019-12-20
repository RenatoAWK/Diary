package com.renatoawk.diary.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Time implements Parcelable {
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
    public Integer getMonth(){return this.calendar.get(Calendar.MONTH)+1;}
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

    public void setTimeStamp(String value){
        value = value.replaceAll("[^0-9-:]"," ");
        String[] timestpam = value.split(" ");
        String[] data = timestpam[0].split("-");
        String[] time = timestpam[1].split(":");
        this.setYear(Integer.valueOf(data[0]));
        this.setMonth(Integer.valueOf(data[1])-1);
        this.setDayOfMonth(Integer.valueOf(data[2]));
        this.setHour(Integer.valueOf(time[0]));
        this.setMinute(Integer.valueOf(time[1]));



    }


    public String getFormatedTime(){
        return fill(this.getHour(),2) +":"+
                fill(this.getMinute(),2);
    }

    private String fill(int number, int lenght){
        StringBuilder value = new StringBuilder(String.valueOf(number));
        while (value.length() < lenght){
            value.insert(0, "0");
        }
        return String.valueOf(value);
    }

    public String getFormatedDate(){

        return fill(this.getDayOfMonth(), 2) +"/"+
                fill(this.getMonth(), 2) +"/"+
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.calendar);
    }

    public Time(Parcel in) {
        this.calendar = (Calendar) in.readSerializable();
    }

    public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel source) {
            return new Time(source);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };
}
