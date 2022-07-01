package com.example.project01_allview.ex02selflistview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.project01_allview.R;

import java.util.ArrayList;

public class TestAdapter extends BaseAdapter {

    ArrayList<TestDTO> list;
    LayoutInflater inflater;

    public TestAdapter(ArrayList<TestDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

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

    //칸마다 보여질 화면을 붙이고 return

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_musiclistview,parent,false);



        return convertView;
    }
}
