package com.example.webScrapper.DateParser;

import android.os.AsyncTask;

import com.example.webScrapper.Models.ListItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class DateParser extends AsyncTask<String, Void, ArrayList<ListItem>> {
    private DateParserInterface dateParserInterface;

    public DateParser(DateParserInterface dateParserInterface) {
        this.dateParserInterface = dateParserInterface;
    }

    @Override
    protected ArrayList<ListItem> doInBackground(String... params) {
        return this.listData(params[0]);

    }

    @Override
    protected void onPostExecute(ArrayList<ListItem> data) {
        super.onPostExecute(data);

        dateParserInterface.onParsingDone(data);
    }

    private ArrayList<ListItem> listData(String url) {
        ArrayList<ListItem> data = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.getElementsByTag("table");
            for (Element table : tables) {
                Elements rows = table.getElementsByTag("tr");

                for (Element row : rows) {
                    Elements links = row.getElementsByTag("a");

                    for (Element link : links) {
                        String urlToSchedule = link.attr("abs:href");
                        String[] parts = urlToSchedule.split(Pattern.quote("plan-"));
                        if (parts.length > 1) {
                            data.add(new ListItem(parts[1], urlToSchedule));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}