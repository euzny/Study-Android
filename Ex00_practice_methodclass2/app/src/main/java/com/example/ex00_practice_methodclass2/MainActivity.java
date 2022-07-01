package com.example.ex00_practice_methodclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //어떤 데이터 타입이든 [] <= 배열 구분(식별)자가 붙으면 배열임
    //멤버는 멤버끼리 접근이 가능함 static => instance 멤버 접근이 일반적으로는 불가
    // 인스턴스 멤버는 모든 멤버에 접근이 가능함.
    // 스테틱 멤버는 스테틱 멤버만 접근이 가능함. (일반)
    // 메모리에 올라가는 순서 때문에 다름.

    int[] ints;
    static ArrayList<Integer> list;  // <> 인스턴스화 할 수 있는 == 객체(Class)가 필요하다.
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ints = null;
        method1();
        list =null;
        Example.str = new ArrayList<>();
        Example.returnEdt();   //프로그램이 시작만해도 메모리에 있는 static 클래스에 .찍어도 나옴
        // array , returnBtn 은 아직 메모리에 없음
        Example ex = new Example();    // 메모리에 생김 ㅣ
        ex.array = new int [5];
        ex.returnBtn();

/*
        Sumclass sc = new Sumclass();
        int hap = 0;
        hap = sc.sum3(1,5);
        Log.d("hap", "두 수 사이의 합: "+hap);
*/

/*        NewSumClass nsc = new NewSumClass();
        nsc.sum(1,2);
        nsc.sum2(1,2);
        nsc.sum3(1,2);*/
        NewSumClass2 nsc = new NewSumClass2();
       int hap= nsc.sum3(3,5);
       nsc.sum2(3,5);
        Log.d("sum2", "두 수 사이의 합: " + hap);

    }
    private void method1(){
            list = null;
    }
    private static void methodStatic(){
        
        //ints = null;  -- 접근불가
        list=null;
        //method1(); -- 접근불가
        /*  이 상태는 접근 가능함
        MainActivity activity = new MainActivity();
        activity.ints =null;
        activity.method1();
         */
    }
}