package com.example.webScrapper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.test.R;
import com.example.webScrapper.Models.ListItem;
import com.example.webScrapper.DateParser.DateParser;
import com.example.webScrapper.DateParser.DateParserInterface;
import com.example.webScrapper.Models.ScheduleRow;
import com.example.webScrapper.Models.YearSchedule;
import com.example.webScrapper.ScheduleParser.ScheduleParser;
import com.example.webScrapper.ScheduleParser.ScheduleParserInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DateParserInterface, ScheduleParserInterface {
    private ArrayAdapter<String> adapter;
    private ProgressBar progressBar;
    private ListView datesList;
    private ArrayList<String> urls = new ArrayList<>();
    private final DateParser datesParser = new DateParser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        datesParser.execute("https://inf.ug.edu.pl/zaoczne-plan");
        datesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ScheduleParser scheduleParser;
                scheduleParser = getNewInstanceOfScheduleParser();
                scheduleParser.execute(urls.get(position));
            }
        });
    }

    @Override
    public void onParsingDone(ArrayList<ListItem> listData) {
        setListItems(listData);
    }

    @Override
    public void onParsingScheduleDone(ArrayList<YearSchedule> schedule) {
        ArrayList<String> studentsYears = new ArrayList<>();
        ArrayList<ScheduleRow> rows = new ArrayList<>();
        setStudensYearsAndRows(schedule,studentsYears, rows);

        Bundle bundle = new Bundle();
        bundle.putSerializable("rows", rows);

        Intent intent = new Intent(this, YearActivity.class);
        intent.putExtra("yearsList", studentsYears);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void setStudensYearsAndRows(ArrayList<YearSchedule> schedule, ArrayList<String> studentsYears, ArrayList<ScheduleRow> rows ) {
        for (YearSchedule s : schedule) {
            studentsYears.add(s.getYear());
            ArrayList<ScheduleRow> y = s.getSchedule();
            for (ScheduleRow b : y) {
                rows.add(b);
            }
        }
    }

    private ScheduleParser getNewInstanceOfScheduleParser() {
        return new ScheduleParser(this);
    }

    private void setListItems(ArrayList<ListItem> listData) {
        ArrayList<String> dates = new ArrayList<>();
        for (ListItem data : listData) {
            dates.add(data.getDate());
            urls.add(data.getUrl());
        }

        adapter = new ArrayAdapter<>(this, R.layout.row, dates);

        datesList.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        datesList = findViewById(R.id.list);
    }
}
