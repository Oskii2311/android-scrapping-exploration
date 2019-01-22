package com.example.webScrapper;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.test.R;
import com.example.webScrapper.Models.ScheduleRow;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
private TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Bundle bundleRows = getIntent().getExtras();
        final ArrayList<ScheduleRow> schedule = (ArrayList<ScheduleRow>) bundleRows.getSerializable("schedule");

        table = findViewById(R.id.table);
        for(ScheduleRow lecture: schedule) {
            setTableSettings(lecture);
        }
    }

    private void setTableSettings(ScheduleRow lecture) {
        TableRow row = new TableRow(this);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);

        TextView tv = new TextView(this);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);

        tv.setText(lecture.getHours());
        tv1.setText(lecture.getType());
        tv2.setText(lecture.getDescription());
        tv2.setPaintFlags(tv2.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv2.setPadding(0,0,0,8);

        table.addView(row);
        table.addView(row1);
        table.addView(row2);

        row.addView(tv);
        row1.addView(tv1);
        row2.addView(tv2);
    }

}
