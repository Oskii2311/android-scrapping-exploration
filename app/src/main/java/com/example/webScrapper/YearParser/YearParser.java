package com.example.webScrapper.YearParser;

import android.os.AsyncTask;
import android.widget.AdapterView;

import com.example.webScrapper.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class YearParser extends AsyncTask<String, Void, ArrayList<String>> {
    private YearParserInterface yearParserInterface;

    public YearParser(MainActivity yearParserInterface) {
        this.yearParserInterface = yearParserInterface;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        return this.studentsYears(params[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<String>  data) {
        super.onPostExecute(data);

        yearParserInterface.onParsingYearDone(data);
    }

    private ArrayList<String> studentsYears(String url) {
        ArrayList<String> data = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Element content = doc.getElementById("middle");
            Elements artBody = content.getElementsByClass("artBody");
            Elements headers = artBody.first().getElementsByTag("h1");

            for (Element header : headers) {
                data.add(header.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}