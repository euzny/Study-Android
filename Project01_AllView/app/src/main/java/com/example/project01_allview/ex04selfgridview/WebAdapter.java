package com.example.project01_allview.ex04selfgridview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project01_allview.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WebAdapter extends BaseAdapter {

    ArrayList<WebDTO> list;
    LayoutInflater inflater;

    public WebAdapter(ArrayList<WebDTO> list, LayoutInflater inflater) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_webgridview,parent,false);
        ImageView imgv_toon = convertView.findViewById(R.id.imgv_toon);
        TextView tv_name =convertView.findViewById(R.id.tv_name);
        TextView tv_score = convertView.findViewById(R.id.tv_score);
        TextView tv_writer = convertView.findViewById(R.id.tv_writer);
        Glide.with(convertView).load(list.get(position).getUrl()).into(imgv_toon);
        tv_name.setText(list.get(position).getName());
        tv_score.setText("★"+list.get(position).getScore());
        tv_writer.setText(list.get(position).getWriter());

        return convertView;
    }
}
