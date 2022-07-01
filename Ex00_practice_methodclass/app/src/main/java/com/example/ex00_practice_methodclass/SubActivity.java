package com.example.ex00_practice_methodclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Button btn = findViewById(R.id.btn1);
        // btn.(전체 멤버 . im 인스턴스 멤버들)
        //Button. static
        //Button.setOn // onclicklistener 사용 불가
        //btn.setOnClickListener();  사용가능
        Examplee ex =  new Examplee();
        ex.array= new int[5];
        Examplee.str = new ArrayList<>();
        ex.rbtn();
        Examplee.redt();


        /*
        *   int[] arr = new int[4];
        *   int arr2[] ={1,2,3,4}; //1차원 배열은 요소로 데이터만 가짐
        *   int [][] arr22 = new int[2][4]; //2차원 배열은 요소로 1차원 배열을 가짐
        * */



    }
}