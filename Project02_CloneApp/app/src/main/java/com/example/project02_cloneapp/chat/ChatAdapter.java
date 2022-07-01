package com.example.project02_cloneapp.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project02_cloneapp.MainActivity;
import com.example.project02_cloneapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatAdapter  extends  RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    LayoutInflater inflater;   //일반 클래스 타입(어댑터)에서는 생성할 수가 없음
    ArrayList<ChatDTO> list;


    public ChatAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public ChatAdapter(LayoutInflater inflater, ArrayList<ChatDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    //ViewHolder == 위젯DTO(텍스트뷰,이미지뷰,리사이클러뷰....)
    //getItemCount에 있는 숫자만큼 화면을 붙임.↓ (화면을 붙일때 위젯을 묶어놓은 ViewHolder를 만들어서
    //가지고 있음 )
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = inflater.inflate(R.layout.item_chat , parent , false);
        //ViewHolder holder = new ViewHolder(v);
        //return holder;
        return new ViewHolder(inflater.inflate(R.layout.item_chat , parent , false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemview).load(list.get(position).getImg_url()).into(holder.imgv_profile);
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_time.setText(list.get(position).getDate());
        holder.tv_msg.setText(list.get(position).getMsg());
    }

    //onCreateViewHolder 몇개를 붙일껀지를 지정함 ( ==> list.size() 데이터 갯수 만큼 )
    @Override
    public int getItemCount() {
        return list.size();
    }


    // -List 자료 구조 ( 추후 )
    // -Layout을 붙여서 View에 넣을수있는 객체 ( LayoutInflater ) ※ 디자인 확인 가능
    // -Context ( 토스트 창 , 새로운 액티비티 연결 등의 기능이 필요한 경우 )



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_time,tv_msg;
        ImageView imgv_profile;
        View itemview;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            this.itemview = itemView;
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            imgv_profile = itemView.findViewById(R.id.imgv_profile);
        }
    }
}
