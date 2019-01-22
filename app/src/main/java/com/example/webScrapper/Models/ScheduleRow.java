package com.example.webScrapper.Models;

import java.io.Serializable;

public class ScheduleRow implements Serializable {
    private String hours;
    private String name;
    private String description;
    private String year;
    private String type;

    public ScheduleRow(String year, String hours, String name, String type, String description) {
        this.year = year;
        this.hours = hours;
        this.name = name;
        this.type = type;
        this.description = description;

    }

    public String getYear() {
        return this.year;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getHours() {
        return this.hours;
    }

    public String getDescription() {
        return this.description;
    }
}
