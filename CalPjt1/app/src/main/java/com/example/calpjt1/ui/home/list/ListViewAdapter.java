package com.example.calpjt1.ui.home.list;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;


import com.example.calpjt1.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ImageView iconImageView;
    private TextView titleTextView;
    private TextView contentTextView;

    //Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>();

    public ListViewAdapter(){

    }

    //Adapter에 사용되는 데이터 개수 리턴
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    //positio에 위치한 데이터를 화면에 출력하는데 사용될 view 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
    }
        //화면에 표시될 view(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        titleTextView = (TextView) convertView.findViewById(R.id.title);
        iconImageView = (ImageView) convertView.findViewById(R.id.icon);
        contentTextView = (TextView) convertView.findViewById(R.id.text);

        ListViewItem listViewItem = listViewItemList.get(position);

        //아이템 내에 각 위젯에 데이터 반영
        titleTextView.setText(listViewItem.getTitle());
        iconImageView.setImageResource(listViewItem.getIcon());
        contentTextView.setText(listViewItem.getContent());

//        LinearLayout cmdArea= (LinearLayout)convertView.findViewById(R.id.cmdArea);
//        cmdArea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(),listViewItemList.get(pos).getContent(),Toast.LENGTH_SHORT).show();
//            }
//        });


        return convertView;
    }

    //지정한 position에 있는 데이터와 관계된 아이템(row)의 ID를 맅 ㅓㄴ
    @Override
    public long getItemId(int position) {
        return position;
    }

    //지정한 위치에 있는 데이터 리턴
    @Override
    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    //아이템 데이터 추가를 위한 함수
    public void addItem(String title, int icon, String content){
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setIcon(icon);
        item.setContent(content);

        listViewItemList.add(item);
    }

}
