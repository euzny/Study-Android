package com.example.project02_cloneapp.friend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_cloneapp.R;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    LayoutInflater inflater; //레이아웃 붙이려면 꼭 필요함(Adapter, Fragment ..)
    ArrayList<FriendDTO> list;
    Context context;   //<= null인 상태에서 어댑터가 생성되면 무조건 꺼짐

    public FriendAdapter(LayoutInflater inflater, ArrayList<FriendDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

/*    public FriendAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public FriendAdapter(LayoutInflater inflater, ArrayList<FriendDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_friend, parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //onCreateViewHolderd 에서 생성해놓은 ViewHolder를 그대로 다시 파라메터로 넘겨줌 + position
        holder.imgv_profile.setImageResource(list.get(position).getImgid());
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_msg.setText(list.get(position).getMsg());
        
        holder.imgv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 중요 Context <= 현재 화면이 띄워져있는 객체로 레이아웃붙이기(LayoutInflater) , 프래그먼트 붙이기(getSupportFragmentManager)
                //startActivity // 기타 등등의 기능을 가지고 있는 객체
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
                //일반 클래스에서는 화면을 붙이고 또는 새로운 액티비티를 띄우는 등의 기능을
               // 사용할수가없으니 해당하는 기능을 사용할수있는 Activity또는 Context를 받아온다.
            }
        });
    }

    @Override //디자인 확인시 return 아무숫자 ==> 리스트가 생기면(DB) list.size로 변경
    public int getItemCount() {
        return list.size();
    }

    //1. ViewHolder라는 중첩(내부)클래스 만들기
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgv_profile;  //현재 null
        TextView tv_name, tv_msg; //현재 null

        public ViewHolder(@NonNull View itemView) {
            super(itemView); //생성자에서 find를 통해서 위젯들을 null이 아닌 상태로 만듦
            imgv_profile = itemView.findViewById(R.id.imgv_profile);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_msg = itemView.findViewById(R.id.tv_msg);
        }
    }

}
