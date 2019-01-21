package com.example.webScrapper.ScheduleParser;

import com.example.webScrapper.Models.ScheduleRow;
import com.example.webScrapper.Models.YearSchedule;

import java.util.ArrayList;

public interface ScheduleParserInterface {
    void onParsingScheduleDone(ArrayList<YearSchedule> Schedule);

}
