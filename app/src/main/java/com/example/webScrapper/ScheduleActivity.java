package com.example.webScrapper;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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
        for (ScheduleRow lecture : schedule) {
            setTableSettings(lecture);
        }
    }

    private void setTableSettings(ScheduleRow lecture) {
        table.setGravity(Gravity.CENTER);
        TableRow[] tableRows = {new TableRow(this), new TableRow(this), new TableRow(this), new TableRow(this)};
        TextView[] textViews = {new TextView(this), new TextView(this), new TextView(this), new TextView(this)};

        LayerDrawable bottomBorder = getBorders(
                0xFFFAFAFA,
                0xFF000000,
                0,
                0,
                0,
                2
        );

        for (TableRow row : tableRows) {
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }

        for (TextView textView : textViews) {
            textView.setWidth(TableLayout.LayoutParams.WRAP_CONTENT);
        }

        textViews[0].setText(lecture.getHours());
        textViews[0].setTextSize(18);
        textViews[0].setTypeface(null, Typeface.BOLD);

        textViews[1].setText(lecture.getName());
        textViews[1].setTextSize(16);
        textViews[1].setTypeface(null, Typeface.BOLD);

        textViews[2].setText(lecture.getType());
        textViews[2].setTextSize(16);
        textViews[2].setTypeface(null, Typeface.BOLD);

        if (lecture.getType().isEmpty()) {
            tableRows[2].setVisibility(View.GONE);
        }

        textViews[3].setText(lecture.getDescription());
        textViews[3].setPadding(0, 0, 0, 20);
        textViews[3].setTextSize(16);
        textViews[3].setBackground(bottomBorder);

        for (int i = 0; i < tableRows.length; i++) {
            if (i != 3) {
                textViews[i].setPadding(0, 0, 0, 4);
            }
            table.addView(tableRows[i]);
            tableRows[i].addView(textViews[i]);
        }
    }

    private LayerDrawable getBorders(int bgColor, int borderColor,
                                     int left, int top, int right, int bottom) {
        ColorDrawable borderColorDrawable = new ColorDrawable(borderColor);
        ColorDrawable backgroundColorDrawable = new ColorDrawable(bgColor);

        Drawable[] drawables = new Drawable[]{
                borderColorDrawable,
                backgroundColorDrawable
        };

        LayerDrawable layerDrawable = new LayerDrawable(drawables);

        layerDrawable.setLayerInset(
                1,
                left,
                top,
                right,
                bottom
        );

        return layerDrawable;
    }
}
