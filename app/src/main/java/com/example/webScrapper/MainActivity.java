package com.example.webScrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test.R;
import com.example.webScrapper.ScheduleParser.ScheduleParser;
import com.example.webScrapper.ScheduleParser.ScheduleParserInterface;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ScheduleParserInterface {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.result);

        new ScheduleParser(this).execute();
    }

    @Override
    public void onParsingDone(ArrayList<String> months) {
        textView.setText(months.toString());
    }
}
