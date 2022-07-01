package com.example.ex13_listview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.ContentView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ex13_listview.listvcus.CustomAdapter;
import com.example.ex13_listview.listvcus.CustomDTO;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflater.inflate(layout) == setContentView(layout)
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);
        ListView listView = rootview.findViewById(R.id.listview);
        Button btn_search = rootview.findViewById(R.id.btn_search);
        ArrayList<CustomDTO> list = new ArrayList<CustomDTO>();
        list.add(new CustomDTO("홍길_동","상메", android.R.drawable.ic_dialog_alert));
        list.add(new CustomDTO("박문_수","가나다라", android.R.drawable.ic_lock_idle_lock));
        list.add(new CustomDTO("심청_","상메", android.R.drawable.ic_lock_idle_alarm));
        list.add(new CustomDTO("강감_찬","!!!!", android.R.drawable.checkbox_off_background));
        list.add(new CustomDTO("김철_수","????", android.R.drawable.ic_notification_overlay));
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  LayoutInflater inflater1 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE); //** getActivity()
                // Context <= 화면에서 제어될때 많은 기능들을 담고 있는 클래스
                //화면 제어권(기능이 제약 된) Fragment 기준 기능이 부족하다면 나의 상위 액티비티에 기능들을 가지고 오는 메소드가 있다.
                // getActivity , getContext <- 상위 액티비티 기능 가져오는 메소드
                //Toast.makeText(getContext(), "gg",Toast.LENGTH_LONG).show();
                CustomAdapter adapter = new CustomAdapter(inflater,list);
                listView.setAdapter(adapter);
            }
        });
        return rootview;
    }
}