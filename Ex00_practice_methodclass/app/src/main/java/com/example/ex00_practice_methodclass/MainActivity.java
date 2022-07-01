package com.example.ex00_practice_methodclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 =findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);

        PracticeCls ps = new PracticeCls();  // instance member iv  => 인스턴스화 시킴
        ps.value1 = "A";                  
        PracticeCls.value2 = "B";  //static member sv => 항상 클래스에만 접근해도 사용가능

        ps.method1();
        PracticeCls.method2();

        //value 3~5 까지 각각의 값에 +1 해보기
        //변수를 사용할 수 없다면 왜 안될까?

        int value5= ps.getValue5() + 1;

        ps.value3++;                // public  -- 어디서든 접근 가능
        ps.value4++;                //default  -- 같은 패키지
        //ps.value5++;                  //private -- 내부에서만 사용, 멤버가 아님
        //public, default 형태의 getter & setter


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //글씨 바꾸기 .setText
             //   btn1.setText("버튼 글씨를 바꿈");
                //String str = "내가 붙인 글씨";
                //changeText( "내가 붙인 글씨");
                //Button btn = null;
                // 변수를 초기화 아무것도 없는 상태 null == Button x
                //Button.setText("String");   // 메모리에 어떤 객체가 올라와서 참조
                //null.setText("String");       // 메모리에 아무것도 없음 null 인 상태
                changeText(btn1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(btn2);
            }
        });  //btn2

        //1. 메소드 안에는 클래스를 중첩하지 않은 경우에는 메소드를 넣을 수 없다.
        //★ 클래스의 중괄호 사이에 만든다.
        // 2. 메소드를 정의(만들어 놓은 부분) + "=" + 메소드를 호출(값을 주는 부분) = 변수 초기화식
        //String str ="내가 붙인 글씨";
        //3. 내가 알고있는 모든 것들은 파라미터부에 쓸 수 있음
        //4. 메소드는 파라메터의 갯수나 타입이 다르면 같은 이름으로 중첩 시킬 수 있다.
        // 메소드 오버로딩 <= 면접에 가끔 물어봄(java, Spring)

    }//onCreate Method
    void changeText(String str){
        btn1.setText(str +"메소드로 바꿔봄");
    }

    void changeText(Button btn){
        btn.setText("버튼을 파라메터로 입력받아서 사용해봄");
    }
    
}