package com.example.webScrapper.ScheduleParser;

import com.example.webScrapper.Models.SpinnerOption;

import java.util.ArrayList;

public interface ScheduleParserInterface {

    void onParsingDone(ArrayList<SpinnerOption> months);
}