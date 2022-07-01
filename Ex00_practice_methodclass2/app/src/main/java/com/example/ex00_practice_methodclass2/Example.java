package com.example.ex00_practice_methodclass2;

import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Example {

    int [] array;
    static ArrayList<String> str;

    // return 타입이 필요한 경우 (= 메소드를 실행하고나서 어떠한 결과를 받아야하는 경우 무조건 리턴타입이 필요함
    //로그를 찍어봄 (리턴 필요없음)
    
    Button  returnBtn(){  //리턴타입 있음 = 메소드에 반드시 return   //리턴타입 없음 = 메소드는 반환(return) 없음
        return null;
    }

    static EditText returnEdt(){
        return null;
    }
    
    /*void method2(int x){
        if(x==0){
            return ;  // 메소드를 빠져나가라
        }
        int abc = 10;  // <- 실행하지 말고 넘어가라
    }*/



}
