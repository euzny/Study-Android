package com.example.project01_allview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project01_allview.ex01listview.ListFragment;
import com.example.project01_allview.ex02selflistview.MusicFragment;
import com.example.project01_allview.ex03gridview.GridFragment;
import com.example.project01_allview.ex04selfgridview.GridSelfFragment;
import com.example.project01_allview.ex05recyclerview.RecyclerFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.BlockingDeque;

public class MainActivity extends AppCompatActivity {
    TabLayout tab_layout;
    // 변수를 선언 (TabLayout) 메모리 2개의 영역이 있는데 이름으로 예약해둠


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ImageView imgv = findViewById(R.id.imgv);
       Glide.with(this).load("https://c.tenor.com/vxJjiiRh3CUAAAAd/%EC%B6%98%EC%8B%9D-%EC%B6%98%EC%8B%9D%EC%9D%B4.gif").into(imgv);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ListFragment()).commit();
        tab_layout = findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setText("리스트 뷰").setId(0));
        tab_layout.addTab(tab_layout.newTab().setText("그리드 뷰").setId(1));
        tab_layout.addTab(tab_layout.newTab().setText("리사이클러 뷰").setId(2));
        tab_layout.addTab(tab_layout.newTab().setText("뷰 페이지").setId(3));
        tab_layout.addTab(tab_layout.newTab().setText("내 리스트뷰").setId(4));
        tab_layout.addTab(tab_layout.newTab().setText("내 그리드뷰").setId(5));
        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //1. 탭이 선택됨
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new ListFragment()).commit();
                }else if(tab.getPosition() ==1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new GridFragment()).commit();
                }else if(tab.getPosition() ==2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new RecyclerFragment()).commit();
                }else if(tab.getPosition() ==3){

                }else if(tab.getPosition()==4){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new MusicFragment()).commit();
                }else if(tab.getPosition()==5){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new GridSelfFragment()).commit();
                }
                Log.d("탭", "onTabSelected: ");
            }

            //2. 선택이 안됨
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("탭", "onTabUnselected: ");
            }

            //3. 탭이 눌린상태에서 또 선택됨
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("탭", "onTabReselected: ");
            }
        });
    }
}