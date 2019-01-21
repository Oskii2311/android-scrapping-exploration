package com.example.webScrapperSecondView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.test.R;
import com.example.webScrapper.DateParser.DateParser;
import com.example.webScrapper.MainActivity;
import com.example.webScrapper.Models.ScheduleRow;
import com.example.webScrapper.Models.YearSchedule;
import com.example.webScrapper.ScheduleParser.ScheduleParser;
import com.example.webScrapper.ScheduleParser.ScheduleParserInterface;
import com.example.webScrapperScheduleView.ScheduleActivity;

import java.util.ArrayList;
import java.util.Collections;

public class YearActivity extends AppCompatActivity{
    private ArrayAdapter<String> adapter;
    private ListView datesList;
    private TextView results;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
        final ArrayList<String> yearsList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
        Bundle bundleRows = getIntent().getExtras();
        final ArrayList<ScheduleRow> rows = (ArrayList<ScheduleRow>) bundleRows.getSerializable("rows");
        datesList = findViewById(R.id.list);
//        results.setText(k.toString());
//        ArrayList<String> years = new ArrayList<>();
//        for(YearSchedule year: YearsList) {
//            years.add(year.getYear());
//        }
////
                adapter = new ArrayAdapter<>(this, R.layout.row, yearsList);
        datesList.setAdapter(adapter);

        datesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ArrayList<ScheduleRow> k = new ArrayList<>();

                for(ScheduleRow i : rows)  {
                    if(i.s().equals(yearsList.get(position))) {
                        k.add(i);
                    }
                }


                xxx();

                Bundle bundle = new Bundle();
                bundle.putSerializable("rows", k);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    private void xxx() {
        intent = new Intent(this, ScheduleActivity.class);
    }



}
