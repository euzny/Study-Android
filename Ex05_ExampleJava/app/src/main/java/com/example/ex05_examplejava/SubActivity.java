package com.example.ex05_examplejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {
    EditText edt_text1, edt_text2;
    Button btn_plus, btn_sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edt_text1 = findViewById(R.id.edt_text1);
        edt_text2 = findViewById(R.id.edt_text2);
        btn_plus = findViewById(R.id.btn_plus);
        btn_sum = findViewById(R.id.btn_sum);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //instance <= new를 통해서 메모리에 올려야만 호출할수 있는 영역
                    // static <= new가 필요없이 클래스에 접근만해도 됨.
                    //멤버 == 클래스 뒤에다가 .찍으면 나오는 변수, 메소드 등
                    // 인스턴스 멤버 = 인스턴스화 된 (변수화) 객체 뒤에 .
                    // 스테틱 멤버 = 클래스 뒤에 . 찍으면 나오는 변수, 메소드 등.
                 //   Test.method3(); //static-method3
                    Test test = new Test();
                    // test.method1(); // method1
                    //test.method2(); - 호출안됨 private 접근 제한자로 외부에서는 접근 x
                    //Test 클래스에 String을 입력받아서 int return 메소드를 자유롭게 (이름) 만들고
                    // edt1+edt2 합을 구할 수 있게 프로그램
                    int sum = test.chStr(edt_text1.getText()+"") + test.chStr(edt_text2.getText()+"");
                     Log.d("sum", sum+"");
                    //응용문제
                    int result = Test.parseEdt(edt_text1)+Test.parseEdt(edt_text2);
                     Log.d("result", result+"");
            }
        }); //btn_plus

        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test test = new Test();
                int sum = test.xToFromy(Integer.parseInt(edt_text1.getText()+""),Integer.parseInt(edt_text2.getText()+""));
                Log.d("두 수 사이의 합",sum+"");
            }
        }); //btn_sum


    }   //onCreate


} //Activity