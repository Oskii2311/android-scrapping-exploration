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
            case SPINNERDATA:
                return "SPINNERDATA";
            case SCHEDULE:
                return "SCHEDULE";
            default:
                return null;
        }
    }
}

