package com.example.webScrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.test.R;
import com.example.webScrapper.Models.FetchConfiguration;
import com.example.webScrapper.Models.FetchType;
import com.example.webScrapper.Models.SpinnerOption;
import com.example.webScrapper.ScheduleParser.ScheduleParser;
import com.example.webScrapper.ScheduleParser.ScheduleParserInterface;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ScheduleParserInterface {
    private ArrayAdapter<String> adapter;
    private ProgressBar progressBar;
    private ListView datesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        FetchConfiguration fetchConfig = new FetchConfiguration(FetchType.SPINNERDATA, "https://inf.ug.edu.pl/zaoczne-plan");
        new ScheduleParser(this).execute(fetchConfig);
    }

    @Override
    public void onParsingDone(ArrayList<SpinnerOption> SpinnerData) {
        ArrayList<String> date= new ArrayList<>();
        for(SpinnerOption data: SpinnerData) {
            date.add(data.getDate());
        }
        adapter = new ArrayAdapter<>(this, R.layout.row,date);

        datesList.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        datesList = findViewById(R.id.list);
    }
}
