package com.example.ex05_examplejava;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public class Test {
        //멤버(클래스 내부에 있는 변수, 메소드, 클래스 등등 전부)
        //멤버 1. i <- 2. s <-
        //static 이냐 static이 아니냐만 구분하면 됨.

    void method1(){
            Log.d("로그", "method1: 호출됨 ");
    }

    private void method2(){
            Log.d("로그", "method2: 호출됨 ");
    }

    static void method3(){
        Log.d("로그", "method3: 호출됨 ");
    }

    //Test 클래스에 String을 입력받아서 int return 메소드를 자유롭게 (이름) 만들고
    // edt1+edt2 합을 구할 수 있게 프로그램

    int chStr (String str1){
        try{
            return Integer.parseInt(str1);
        }catch(Exception e){
            return 0;
        }
    }

    static int  parseEdt(EditText edt){
        try{
            return Integer.parseInt(edt.getText()+"");
        }catch(Exception e){
            return 0;
        }

    }
    public int xToFromy(int num1, int num2){
        int sum = 0;
        for(int i= num1; i<= num2; i++){
            sum+=i;
        }
        return sum;
    }


}
