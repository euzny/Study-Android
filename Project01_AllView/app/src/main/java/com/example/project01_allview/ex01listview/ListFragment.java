package com.example.project01_allview.ex01listview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.project01_allview.R;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    ListView listView;
    Button btn_insert, btn_delete;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        //layout을 viewGroup에 담아서 (findViewById 하기 위해서) 사용
        listView = viewGroup.findViewById(R.id.listview);
        btn_insert = viewGroup.findViewById(R.id.btn_insert);
        btn_delete = viewGroup.findViewById(R.id.btn_delete);
        ArrayList<ListDTO> list = new ArrayList<>();

        list.add(new ListDTO(R.drawable.ic_launcher_background,"채팅방이름","채팅방 메시지입니다2.","오후 16:08"));
        list.add(new ListDTO(R.drawable.ic_launcher_background,"채팅방이름","채팅방 메시지입니다3.","오후 16:08"));
        ListAdapter adapter = new ListAdapter(list, inflater);
        listView.setAdapter(adapter);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.list.add(new ListDTO(R.drawable.ic_launcher_background,"채팅방이름","채팅방 메시지입니다.","오후 16:08"));
                adapter.notifyDataSetChanged();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //삭제
                //1. 전체 비우기
                /*adapter.list.clear();
                adapter.notifyDataSetChanged();*/
                //2. 0번째 인덱스 사용중인 데이터만 지우기
                adapter.list.remove(0);
                adapter.notifyDataSetChanged();
            }
        });

        return viewGroup;
    }
}