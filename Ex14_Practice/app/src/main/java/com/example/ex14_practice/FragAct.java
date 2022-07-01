package com.example.ex14_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FragAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
        //Fragment를 하나 생성후 Activity_frag에 붙이기
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ExFragment()).commit();

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //프래그먼트 하나 추가 (이름은 자유롭게 배경은 어두운색)
                // 다음 프래그먼트 버튼 클릭시 만든 프래그먼트로 전환되게끔 처리 . replace();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new NeFragment()).commit();
            }
        });
    }

    void changeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ExFragment()).commit();
    }
}