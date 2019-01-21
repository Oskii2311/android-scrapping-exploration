package com.example.webScrapper.Models;


public class FetchConfiguration {
    FetchType type;
    String url;

    public FetchConfiguration(FetchType type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getConfigurationType() {
        switch (this.type) {
            case LISTDATA:
                return "LISTDATA";
            case SCHEDULE:
                return "SCHEDULE";
            case STUDENTSYEAR:
                return "STUDENTSYEAR";
            default:
                return null;
        }
    }
}

