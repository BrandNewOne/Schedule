package com.example.calpjt1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.calpjt1.AboutFile;
import com.example.calpjt1.AddScheduleActivity;
import com.example.calpjt1.R;
//import com.example.calpjt1.ViewPagerAdapter;
import com.example.calpjt1.ui.home.decorator.EventDecorator;
import com.example.calpjt1.ui.home.decorator.SaturdayDecorator;
import com.example.calpjt1.ui.home.decorator.SundayDecorator;
import com.example.calpjt1.ui.home.decorator.TodayDecorator;
import com.example.calpjt1.ui.home.list.ListViewAdapter;
import com.example.calpjt1.ui.home.list.ListViewItem;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.app.Activity.RESULT_OK;


@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends Fragment {

    private Context context;
    private HomeViewModel homeViewModel;


    //여러가지 xml 물건들
    private ViewPager2 viewPager2;
    private MaterialCalendarView materialCalendarView;
    private ListView listView;
    private Button btn;
    private TextView tv;

    private ListViewAdapter adapter;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        context=container.getContext();

        //viewPager2 생성
//        viewPager2 = root.findViewById(R.id.viewPager2);

        //텍스트뷰 생성
        tv = root.findViewById(R.id.home_txt);
        //리스트뷰 생성
        listView = root.findViewById(R.id.listView);
        //달력 생성
        materialCalendarView = root.findViewById(R.id.calendarView);
        //버튼 생성
        btn = root.findViewById(R.id.button2);

        materialCalendarView.state().edit()
                .isCacheCalendarPositionEnabled(true)
                .setFirstDayOfWeek(1)
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        materialCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);

//        materialCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
//        materialCalendarView.setDynamicHeightEnabled(true);

        try {
            materialCalendarView.addDecorators(
                    new SundayDecorator(),
                    new SaturdayDecorator(),
                    new TodayDecorator(),
                    new EventDecorator(Color.RED, FileList())
            );
        }
        catch (Exception e){
            materialCalendarView.addDecorators(
                    new SundayDecorator(),
                    new SaturdayDecorator(),
                    new TodayDecorator()
            );

        }

        //처음은 오늘날짜 선택
        materialCalendarView.setSelectedDate(CalendarDay.today());

        //리스트뷰 생성
        createListView(formatDay(CalendarDay.today()));

        //날짜 클릭이벤트
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //리스트 뷰 생성
                createListView(formatDay(date));
            }
        });


        //여러가지 리스너 이벤트

        //버튼 클릭 이벤트 등록
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CalendarDay selectDate = materialCalendarView.getSelectedDate();

                Intent intent = new Intent(context, AddScheduleActivity.class);
                intent.putExtra("selectDate", formatDay(selectDate));
                startActivityForResult(intent,0);//액티비티 띄우기

                //Toast.makeText(context,String.valueOf(selectDate.getDate()),Toast.LENGTH_SHORT).show();
            }
        });

        //리스트 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ListViewItem item = (ListViewItem) listView.getItemAtPosition(position);
                Toast.makeText(context,item.getTitle()+item.getContent(),Toast.LENGTH_SHORT).show();
            }
        });

        //제스처 이벤트 등록
        return root;

    }

    //===========================================  나중에 리팩토링 해야함==============================================================
    //캘린더데이를 문자열로
    public static String formatDay(CalendarDay cal)
    {
        // 날짜를 통신용 문자열로 변경
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal.getDate());
    }

    //문자열을 캘린더데이로 변환
    private CalendarDay formatCal(String s){
        String[] a = s.split("-");
        CalendarDay cal=CalendarDay.from(Integer.valueOf(a[0]),Integer.valueOf(a[1])-1,Integer.valueOf(a[2]));

        return cal;
    }


    // 파일리스트 보여줌
    public List<CalendarDay> FileList() {
        String path = context.getFilesDir().toString();
        try {
            File directory = new File(path);
            File[] files = directory.listFiles();

            List<CalendarDay> filesNameList = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                filesNameList.add(formatCal(files[i].getName()));
            }
            return filesNameList;
        }catch (Exception e){
            return null;
        }

    }

    private void createListView(String s){
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        String selectDate=s;

        List<String> fileList = new AboutFile(context).FileList(selectDate);

        if(fileList==null){
            listView.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            tv.setText("일정이 없습니다.");
        }else{
            tv.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            for (String a : fileList) {
                adapter.addItem(a, R.drawable.ic_dashboard_black_24dp, "내용1");
            }
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0){
            if (resultCode==RESULT_OK) {
                //성공적으로 끝냄
                createListView(formatDay(materialCalendarView.getSelectedDate())); //리스트뷰 새로고침
                materialCalendarView.addDecorators(new EventDecorator(Color.RED, FileList()));


            }else{
                //뒤로가기등 성공적으로 못끝냄

            }
        }else if(requestCode==1){
        }
    }

}
