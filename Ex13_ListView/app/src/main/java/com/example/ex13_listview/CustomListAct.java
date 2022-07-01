package com.example.ex13_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ex13_listview.listvcus.CustomAdapter;
import com.example.ex13_listview.listvcus.CustomDTO;

import java.util.ArrayList;

public class CustomListAct extends AppCompatActivity {
    //데이터 ArrayList<E> Elements <= 자체도 커스텀. (내가 원하는 형태)
    // 뷰 layout (칸마다 붙는 데이터 <= 자체도 커스텀. (내가 원하는 형태)
    //1. DTO 여러가지 변수 (내가 화면을 보여줄때 필요한 데이터들을 묶어놓은 객체)
    // 2. Layout 칸마다 붙어서 보여질 화면을 만듦
    //3. 1,2번을 이용해서 어댑터(Adapter)를 만든다.
    //4. ListView <= adapter를 세팅함
    Button btn_search;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        ArrayList<CustomDTO> list = new ArrayList<CustomDTO>();
        list.add(new CustomDTO("홍길동","상메", android.R.drawable.ic_dialog_alert));
        list.add(new CustomDTO("박문수","가나다라", android.R.drawable.ic_lock_idle_lock));
        list.add(new CustomDTO("심청","상메", android.R.drawable.ic_lock_idle_alarm));
        list.add(new CustomDTO("강감찬","!!!!", android.R.drawable.checkbox_off_background));
        list.add(new CustomDTO("김철수","????", android.R.drawable.ic_notification_overlay));

        btn_search = findViewById(R.id.btn_search);
        listview = findViewById(R.id.listview);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                CustomAdapter adapter = new CustomAdapter(inflater, list);
                listview.setAdapter(adapter);
            }
        });
    }
}