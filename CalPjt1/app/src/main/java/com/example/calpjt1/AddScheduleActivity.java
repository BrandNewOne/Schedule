package com.example.calpjt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AddScheduleActivity extends AppCompatActivity {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        final TextView tv = (TextView)findViewById(R.id.textView);
        b=(Button)findViewById(R.id.button);
        Intent intent = getIntent(); /*데이터 수신*/

        final String getSelectDay = intent.getExtras().getString("selectDate");
        tv.setText(getSelectDay);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddScheduleActivity.this,"클릭",Toast.LENGTH_SHORT).show();

                //파일 쓰기
                new AboutFile(getApplicationContext()).writeFile(getSelectDay,"time.txt","오우오우야");
                //파일 읽기
                //tv.setText(new AboutFile(getApplicationContext()).readFile("time.txt"));
                setResult(RESULT_OK);
                finish();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}