package com.example.ex08_layoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    LinearLayout linear; //xml에서 만든 모든 위젯은 별도의 클래스파일이 존재한다.
    //대문자로 시작(Class), 소문자로 시작(Method, variable), 전체 대문자(final variable)
    // is <= returnType boolean, set <= setter, get <= getter

    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String abcd = null; //reference < = 참조 메모리
        //new <= 객체(OBj) 메모리에 공간을 두고 사용할 준비를 함
        // 메모리에 공간을 안 두고 사용준비 x
        //if(abcd.length() == 0){
        //error -> null.length()
        // }

        // setContentView 가 레이아웃을 찾아서 액티비티 화면에 붙이는 과정
        //레이아웃을 인플레이션 한다
        // 레이아웃을 인플레이션(붙임) 처리 하기전에 어떤 위젯을 찾고나서 이벤트를 걸면
        //해당하는 위젯을 null임(사용준비가 안됨)
        // 오류가 났을때는 반드시 파란색 글씨로 표시된 오류코드와 그 윗줄을 반드시 볼 것
        //안드로이드가 어려운 이유 : 안드로이드 os에서 제공해주는 기능을 사용해서 코딩해야함
        // 사용방법을 알고나서 사용하는 것은 쉽지만 백퍼센트 모든 원리를 이해하는것이 어려움
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.btn1);
        linear = findViewById(R.id.linear);  //<= sub1.xml
        relative = findViewById(R.id.relative); //<=sub2.xml
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //★ layout을 붙일때 사용하는 객체 (LayoutInflater) <= os에서 제공
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub1,linear,true);
                //attachToRoot <= layout이 별도로 소스코드(.java) 파일 가지고 있는지 여부
                //true 없음, false 있음
                //findViewById <= '현재 내가 가지고 있는' xml 에서 위젯을 찾아와 자바코드와 연결해줌
                inflater.inflate(R.layout.sub2,relative,true);
                Button sub_btn1= findViewById(R.id.sub_btn1);
                sub_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "토스트",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}