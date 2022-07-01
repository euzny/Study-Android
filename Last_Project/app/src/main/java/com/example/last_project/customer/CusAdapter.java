package com.example.last_project.customer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.last_project.MainActivity;
import com.example.last_project.R;
import com.google.android.material.internal.ContextUtils;

import java.util.ArrayList;
import java.util.List;

public class CusAdapter extends RecyclerView.Adapter<CusAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<CustomerDTO> list;
    Context context;


    public ArrayList<CustomerDTO> getList() {
        return list;
    }

    public void setList(ArrayList<CustomerDTO> list) {
        this.list = list;
    }

    public CusAdapter(LayoutInflater inflater, ArrayList<CustomerDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    public CusAdapter(LayoutInflater inflater, ArrayList<CustomerDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    public CusAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override  //ViewHolder를 만들어내는 구간
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_rev_cus,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_tel.setText(list.get(position).getPhone());
        holder.tv_num.setText(list.get(position).getId()+"");
        holder.tv_name.setText(list.get(position).getName());
        holder.card_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CusDetailActivity.class);
                intent.putExtra("dto",list.get(position));
                context.startActivity(intent);
             /*   Intent intent = new Intent(v.getContext(),CusDetailActivity.class);
                v.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //DTO <= 바뀌어야할 데이터를 가지고 있는 객체
    //ViewHolder <= 바뀌어야할 위젯을 가지고 있는 객체
    public class ViewHolder extends RecyclerView.ViewHolder{   
        TextView tv_num, tv_name, tv_tel;
        ImageView imgv;
        CardView card_cus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_num = itemView.findViewById(R.id.tv_num);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_tel = itemView.findViewById(R.id.tv_tel);
            this.imgv= itemView.findViewById(R.id.imgv);
            this.card_cus = itemView.findViewById(R.id.card_cus);
        }
    }
}
