//package com.example.calpjt1;
//
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//public class ViewHolderPage extends RecyclerView.ViewHolder {
//
//    private TextView tv_title;
//    private RelativeLayout rl_layout;
//
//    DataPage data;
//
//    ViewHolderPage(View itemView) {
//        super(itemView);
//        tv_title = itemView.findViewById(R.id.listView);
//        rl_layout = itemView.findViewById(R.id.rl_layout);
//    }
//
//    public void onBind(DataPage data){
//        this.data = data;
//
//        tv_title.setText(data.getTitle());
//        rl_layout.setBackgroundResource(data.getColor());
//    }
//}