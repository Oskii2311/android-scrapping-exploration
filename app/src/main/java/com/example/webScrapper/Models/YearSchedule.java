package com.example.webScrapper.Models;

import java.util.ArrayList;

public class YearSchedule {
    String year;
    ArrayList<ScheduleRow> schedule;

    public YearSchedule(String year, ArrayList<ScheduleRow> schedule){
        this.year = year;
        this.schedule = schedule;
    }

    public String getYear() {
        return this.year;
    }

    public ArrayList<ScheduleRow> getSchedule() {
        return this.schedule;
    }
}
