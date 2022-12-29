package com.example.CourseWork;

import java.io.Serializable;

public class Date implements Comparable<Date>{
    private int year = -1;
    private int month = -1;
    private int day = -1;

    public void setDate(int y, int m, int d){
            year = y;
            month = m;
            day = d;
    }

    public String getDate(){ return day + "/" + month + "/" + year;}

    @Override
    public int compareTo(Date otherDate) {
        if (year > otherDate.year) return -1;
        if (month > otherDate.month) return -1;
        if (day > otherDate.day) return -1;
        if (day == -1 || otherDate.day == -1) return -1;
        return 1;
    }
}
