package com.example.webScrapper.Models;

import java.io.Serializable;

public class ScheduleRow implements Serializable {
    private String hours;
    private String type;
    private String description;
    private String s;

    public ScheduleRow(String s,String hours, String type, String description) {
        this.s = s;
        this.hours = hours;
        this.type = type;
        this.description = description;
    }

    public String s() {
        return this.s;
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
