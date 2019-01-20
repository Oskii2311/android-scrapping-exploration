package com.example.webScrapper.Models;

public class SpinnerOption {
    private String date;
    private String url;

    public SpinnerOption(String date, String url) {
        this.date = date;
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDate() {
        return this.date;
    }
}
