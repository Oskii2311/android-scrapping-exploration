package com.example.webScrapper.Models;

import java.io.Serializable;

public class ScheduleRow implements Serializable {
    private String hours;
    private String type;
    private String description;
    private String year;

    public ScheduleRow(String year,String hours, String type, String description) {
        this.year = year;
        this.hours = hours;
        this.type = type;
        this.description = description;
    }

    public String getYear() {
        return this.year;
    }


    public String getType() {
        return this.type;
    }

    public String getHours() {
        return this.hours;
    }

    public String getDescription() {
        return this.description;
    }
}
