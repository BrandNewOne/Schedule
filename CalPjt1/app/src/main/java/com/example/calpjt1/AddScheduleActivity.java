package com.example.calpjt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddScheduleActivity extends AppCompatActivity {
    private Button b;
    private EditText scheduleTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        scheduleTitle = (EditText)findViewById(R.id.schedule_title);
        b=(Button)findViewById(R.id.button);
        Intent intent = getIntent(); /*데이터 수신*/

        final String getSelectDay = intent.getExtras().getString("selectDate");
        //scheduleTitle.setText(getSelectDay);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddScheduleActivity.this,"클릭",Toast.LENGTH_SHORT).show();

                //파일 쓰기
                if(scheduleTitle.getText().toString().equals("")){
                    Toast.makeText(AddScheduleActivity.this,"타이틀을 입력해주세요",Toast.LENGTH_SHORT).show();
                }else{
                    new AboutFile(getApplicationContext()).writeFile(getSelectDay,scheduleTitle.getText()+".txt","오우오우야");
                    setResult(RESULT_OK);
                    finish();
                }

                //파일 읽기
                //tv.setText(new AboutFile(getApplicationContext()).readFile("time.txt"));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}