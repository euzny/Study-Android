package com.example.ex00_practice_methodclass2;

import android.util.Log;

public class Sumclass {
    //Class SumClass
    // 클래스 규칙 : 대문자로 시작 <=
    // 변수 규칙 : 소문자로 의미있는 단어가 되게끔 만들기
    // 메소드 규칙 : 소문자로 시작, 의미있는 단어들을 연결해서 대문자로 구분(카멜표기법)
    // ex ) 어떤 수를 합산한 결과값 : return x, return result  , hap, intSum...

/*    int num1 , num2;
    //1. 숫자 두개를 입력받는 메소드를 만드세요. sum
    public void  sum(int num1, int num2){
            this.num1 = num1;
            this.num2= num2;
    }

    //2. 숫자 두개를 입력받는 메소드
    // +기능 두수 사이의 합을 logd에 찍는 메소드. sum2
    public int sum2(int num1, int num2){
       int hap=0;
        sum(num1, num2);
        for(int i=num1; i<=num2; i++){
                hap+=i;
            }
        Log.d("sum2", "두 수 사이의 합: " + hap);
        return hap;
    }
    //3. 숫자 두개를 입력받아서 두수 사이의 합을 logd에 찍고
    //결과를 다시 MainActivity로 받을수있게끔 해주는 메소드. sum3
    public int sum3(int num1, int num2){

        return sum2(num1, num2);
    }*/

    void sum2(int num1, int num2) {
        int hap = 0;
        for (int i = num1; i <= num2; i++) {
            hap += i;
        }
        Log.d("sum2", "두 수 사이의 합: " + hap);
    }
    int sum3(int num1, int num2){
        int hap=0;
        for(int i=num1; i<=num2; i++){
            hap+=i;
        }
        Log.d("hap", "두 수 사이의 합: " + hap);
        return hap;
    }

    String test(){
        return "!!!!!!!!!!!!!!!!!!!!!";
    }

}
