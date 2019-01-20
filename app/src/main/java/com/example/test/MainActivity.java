package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ParserResponseInterface {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.result);

        new Scrapper(this).execute();
    }

    @Override
    public void onParsingDone(ArrayList<String> months) {
        textView.setText(months.toString());
    }
}
