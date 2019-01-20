package com.example.webScrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private Spinner monthsSelect;
    private Spinner daysSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        FetchConfiguration fetchConfig = new FetchConfiguration(FetchType.SPINNERDATA, "https://inf.ug.edu.pl/zaoczne-plan");
        new ScheduleParser(this).execute(fetchConfig);
    }

    @Override
    public void onParsingDone(ArrayList<SpinnerOption> months) {
        ArrayList<String> date= new ArrayList<>();
        for(SpinnerOption month: months) {
            date.add(month.getDate());
        }
        adapter = new ArrayAdapter<>(this, R.layout.row,date);

        monthsSelect.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        monthsSelect = findViewById(R.id.months_select);
        daysSelect = findViewById(R.id.days_select);
        daysSelect.setEnabled(false);
    }
}
