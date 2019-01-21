package com.example.webScrapper.ScheduleParser;

import android.os.AsyncTask;

import com.example.webScrapper.MainActivity;
import com.example.webScrapper.Models.ScheduleRow;
import com.example.webScrapper.Models.YearSchedule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.Year;
import java.util.ArrayList;

public class ScheduleParser extends AsyncTask<String, Void, ArrayList<YearSchedule>> {
    private ScheduleParserInterface scheduleParserInterface;

    public ScheduleParser(MainActivity scheduleParserInterface) {
        this.scheduleParserInterface = scheduleParserInterface;
    }

    @Override
    protected ArrayList<YearSchedule> doInBackground(String... params) {
        return this.getSchedule(params[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<YearSchedule>  data) {
        super.onPostExecute(data);

        scheduleParserInterface.onParsingScheduleDone(data);
    }

    private ArrayList<YearSchedule> getSchedule(String url) {
        ArrayList<YearSchedule> yearsSchedule = new ArrayList<>();
        ArrayList<String> years = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Element content = doc.getElementById("middle");
            Elements artBody = content.getElementsByClass("artBody");
            Elements lists = artBody.first().getElementsByTag("ul");
            Elements headers = artBody.first().getElementsByTag("h1");

            for (Element header : headers) {
                years.add(header.text());
            }
            int counter = 0;

            for (Element list : lists) {
                Elements rows = list.getElementsByTag("li");
                ArrayList<ScheduleRow> data = new ArrayList<>();
                for (Element row : rows) {
                    String[] parts = row.text().split(",");
                    String hours = parts[0];
                    String rest = "";

                    Element lectureType = row.getElementsByTag("b").first();
                    for (int i = 0; i < parts.length; i++) {
                        if (i > 1) {
                            rest += parts[i];
                        }
                    }
                    data.add(new ScheduleRow(years.get(counter), hours, lectureType.text(), rest));
                }
                 yearsSchedule.add(new YearSchedule(years.get(counter), data));
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yearsSchedule;
    }
}
