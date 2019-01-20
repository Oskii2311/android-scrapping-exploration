package com.example.webScrapper.ScheduleParser;

import android.os.AsyncTask;

import com.example.webScrapper.Models.FetchConfiguration;
import com.example.webScrapper.Models.SpinnerOption;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class ScheduleParser extends AsyncTask<FetchConfiguration, Void, ArrayList<SpinnerOption>> {
    private ScheduleParserInterface scheduleParserInterface;

    public ScheduleParser(ScheduleParserInterface scheduleParserInterface) {
        this.scheduleParserInterface = scheduleParserInterface;
    }

    @Override
    protected ArrayList<SpinnerOption> doInBackground(FetchConfiguration... params) {
        if (params[0].getConfigurationType() == "SPINNERDATA") {
            return this.getData(params[0].getUrl());
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<SpinnerOption> data) {
        super.onPostExecute(data);

        scheduleParserInterface.onParsingDone(data);
    }

    private ArrayList<SpinnerOption> getData(String url) {
        ArrayList<SpinnerOption> data = new ArrayList<>();


        try {
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.getElementsByTag("table");
            Elements rows = tables.last().getElementsByTag("tr");

            for (Element row : rows) {
                Elements links = row.getElementsByTag("a");

                for (Element link : links) {
                    String urlToSchedule = link.attr("abs:href");
                    String[] parts = urlToSchedule.split(Pattern.quote("plan-"));

                    data.add(new SpinnerOption(parts[1], urlToSchedule));
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}