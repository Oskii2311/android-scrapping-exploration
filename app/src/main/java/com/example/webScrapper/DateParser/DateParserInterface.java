package com.example.webScrapper.DateParser;

import com.example.webScrapper.Models.ListItem;

import java.util.ArrayList;

public interface DateParserInterface {

    void onParsingDone(ArrayList<ListItem> listItems);
}