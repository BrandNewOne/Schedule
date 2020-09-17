package com.example.calpjt1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.calpjt1.AboutFile;
import com.example.calpjt1.AddScheduleActivity;
import com.example.calpjt1.R;
import com.example.calpjt1.ui.home.decorator.SaturdayDecorator;
import com.example.calpjt1.ui.home.decorator.SundayDecorator;
import com.example.calpjt1.ui.home.decorator.TodayDecorator;
import com.example.calpjt1.ui.home.list.ListViewAdapter;
import com.example.calpjt1.ui.home.list.ListViewItem;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.List;

import static android.app.Activity.RESULT_OK;


@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends Fragment {

    private Context context;
    private HomeViewModel homeViewModel;

    //여러가지 xml 물건들
    private MaterialCalendarView materialCalendarView;
    private ListView listView;
    private Button btn;
    private TextView tv;

    private ListViewAdapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        context=container.getContext();

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


        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new TodayDecorator()
        );

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

//        여기서 데이터 받아오고싶게 만들고싶음
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//        final TextView textView = root.findViewById(R.id.textView);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

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

    private String formatDay(CalendarDay c){
        String year = String.valueOf(c.getYear());
        String month = String.valueOf(c.getMonth()+1);
        String day = String.valueOf(c.getDay());

        return year+month+day;

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


            }else{
                //뒤로가기등 성공적으로 못끝냄

            }
        }else if(requestCode==1){
        }
    }


}
