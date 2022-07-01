package com.example.project01_allview.ex01listview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project01_allview.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    ArrayList<ListDTO> list;  //<= null <= Adapter를 사용하는 쪽에서 데이터를 넘겨 받음
    LayoutInflater inflater;
    //ListView,GridView, Recycle.. index 0부터 시작 <=> ArrayList.index =0

    //해당하는 어댑터 사용하기 위해서 ArrayList, LayoutInflater 하나씩 필요
    public ListAdapter(ArrayList<ListDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    //1. 목록에 보여질 데이터 갯수를 의미
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //화면을 붙여주는 처리 (LayoutInflater) <= Context(액티비티나 화면에 떠있는 객체에서만 생성가능)
        //어댑터를 사용하는 곳에서 받아오자.
        convertView = inflater.inflate(R.layout.item_listview, parent,false);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_msg = convertView.findViewById(R.id.tv_msg);
        TextView tv_time = convertView.findViewById(R.id.tv_time);
        tv_name.setText(list.get(position).getChat_name());
        tv_msg.setText(list.get(position).getChat_msg());
        tv_time.setText(list.get(position).getChat_date());
        
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("어댑터", "onClick: 텍스트뷰 누름");
            }
        });


        return convertView;  //null이면 에러 발생
    }
}
