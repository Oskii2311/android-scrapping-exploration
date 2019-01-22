package com.example.webScrapper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.test.R;
import com.example.webScrapper.Models.ScheduleRow;

import java.util.ArrayList;

public class YearActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ListView datesList;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
        Bundle bundleRows = getIntent().getExtras();
        final ArrayList<String> yearsList = (ArrayList<String>) getIntent().getSerializableExtra("yearsList");
        final ArrayList<ScheduleRow> rows = (ArrayList<ScheduleRow>) bundleRows.getSerializable("rows");

        init();
        createListRows(yearsList);

        datesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ArrayList<ScheduleRow> schedule = new ArrayList<>();

                for (ScheduleRow row : rows) {
                    if (row.getYear().equals(yearsList.get(position))) {
                        schedule.add(row);
                    }
                }

                intent = returnIntent();

                Bundle bundle = new Bundle();
                bundle.putSerializable("schedule", schedule);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    private Intent returnIntent() {
        return new Intent(this, ScheduleActivity.class);
    }

    private void init() {
        datesList = findViewById(R.id.list);
    }

    private void createListRows(ArrayList<String> yearsList) {
        adapter = new ArrayAdapter<>(this, R.layout.row, yearsList);
        datesList.setAdapter(adapter);
    }
}
