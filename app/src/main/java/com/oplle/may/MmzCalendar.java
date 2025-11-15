package com.oplle.may;

import android.content.Context;
import android.widget.GridView;


public class MmzCalendar {
    private int month;
    private int year;

    public MmzCalendar() {
        this.month = 1;
        this.year = 2000;
    }
    public MmzCalendar(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public String getMonth() {
        return month+"";
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return month + " " + year;
    }
}

