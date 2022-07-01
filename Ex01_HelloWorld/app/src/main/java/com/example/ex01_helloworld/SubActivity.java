package com.example.ex01_helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    
    Button btnsub ;    //전역 변수, 필드  // 내가 알고있는 대부분의 것들은 메소드의 return, 필드, 파라미터
   static Button button2;
    // 클래스의 멤버 <= 클래스에 접근해야지만 사용할 수 있는 영역
    // 인스턴스 멤버 <= 클래스를 인스턴스화시켜야만 쓸 수 있음 == new SubActivity();
    // 스테틱 멤버 <= 클래스를 인스턴스화 안하고 접근만 해서 사용 가능 == SubActivity.button2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
       // Button btnsub ; //지역 변수 : 메소드 안에서만 사용가능
        btnsub = findViewById(R.id.btnsub);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSub2();
            }
        });
    }

    //                  메소드 이름이 회색이면 만들어 놓고 호출 안 함 (안 씀)
    public void goSub2(){
        Intent intent = new Intent(SubActivity.this , SubActivity2.class);
        startActivity(intent);
    }
}