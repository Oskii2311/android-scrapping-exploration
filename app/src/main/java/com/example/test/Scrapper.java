package com.example.test;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class Scrapper extends AsyncTask<Void, Void, ArrayList<String>> {
    private ParserResponseInterface parserResponseInterface;
    private Elements rows;
    private ArrayList<String> months = new ArrayList<>();

    public Scrapper(ParserResponseInterface parserResponseInterface) {
        this.parserResponseInterface = parserResponseInterface;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {

        try {
            Document doc = Jsoup.connect("https://inf.ug.edu.pl/zaoczne-plan").get();
            Elements tables = doc.getElementsByTag("table");
            rows = tables.first().getElementsByTag("tr");
            for (Element row : rows) {
                months.add(row.getElementsByTag("td").first().text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return months;
    }

    @Override
    protected void onPostExecute(ArrayList<String> months) {
        super.onPostExecute(months);

        parserResponseInterface.onParsingDone(months);
    }
}