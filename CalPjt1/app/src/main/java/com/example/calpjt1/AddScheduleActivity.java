package com.example.calpjt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        TextView tx = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent(); /*데이터 수신*/

        String getSelectDay = intent.getExtras().getString("selectDate");
        tx.setText(getSelectDay);
    }
}