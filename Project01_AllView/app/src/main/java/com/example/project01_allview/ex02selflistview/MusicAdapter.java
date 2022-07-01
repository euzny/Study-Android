package com.example.project01_allview.ex02selflistview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project01_allview.R;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {

    ArrayList<MusicDTO> list ;
    LayoutInflater inflater;

    public MusicAdapter(ArrayList<MusicDTO> list, LayoutInflater inflater) {
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
        convertView = inflater.inflate(R.layout.item_musiclistview,parent,false);
        //https://t1.daumcdn.net/cfile/blog/2455914A56ADB1E315

        TextView tv_rank = convertView.findViewById(R.id.tv_rank);
        TextView tv_title = convertView.findViewById(R.id.tv_title);
        TextView tv_signer = convertView.findViewById(R.id.tv_singer);
        TextView tv_album = convertView.findViewById(R.id.tv_album);
        TextView tv_like = convertView.findViewById(R.id.tv_like);
        ImageView imgv_album = convertView.findViewById(R.id.imgv_album);
        Glide.with(convertView).load("https://c.tenor.com/vxJjiiRh3CUAAAAd/%EC%B6%98%EC%8B%9D-%EC%B6%98%EC%8B%9D%EC%9D%B4.gif").into(  imgv_album);
        tv_rank.setText((position+1)+"");
        //imgv_album.setImageResource(list.get(position).getResId());
        tv_like.setText(list.get(position).getLike());
        tv_album.setText(list.get(position).getAlbum());
        tv_signer.setText(list.get(position).getSinger());
        tv_title.setText(list.get(position).getTitle());
        return convertView;
    }
}
