package com.example.webScrapper.LecturesParser;

import android.os.AsyncTask;

import com.example.webScrapper.MainActivity;
import com.example.webScrapper.YearParser.YearParserInterface;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class LectureParser extends AsyncTask<String, Void, ArrayList<String>> {
    private LectureParserInterface lectureParserInterface;

    public LectureParser(MainActivity lectureParserInterface) {
        this.lectureParserInterface = lectureParserInterface;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        return this.studentsYears(params[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<String> data) {
        super.onPostExecute(data);

        lectureParserInterface.onParsingLecturesDone(data);
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
