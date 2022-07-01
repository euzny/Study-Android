package com.example.project02_cloneapp.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project02_cloneapp.R;
import com.example.project02_cloneapp.chat.ChatAdapter;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<ShopDTO> list;

    public ShopAdapter(LayoutInflater inflater, ArrayList<ShopDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_shop,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder holder, int position) {
            holder.tv_name.setText(list.get(position).getName());
            holder.tv_price.setText(list.get(position).getPrice());
        Glide.with(holder.itemview).load(list.get(position).getImg_url()).load(holder.imgv_product);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_price;
        ImageView imgv_product;
        View itemview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_price =itemView.findViewById(R.id.tv_price);
            imgv_product =itemView.findViewById(R.id.imgv_product);
        }
    }
}
