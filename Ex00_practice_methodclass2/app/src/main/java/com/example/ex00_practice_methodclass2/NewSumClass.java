package com.example.ex00_practice_methodclass2;

public class NewSumClass extends  Sumclass{
    //기존에 SumClass <= 객체가 잘 작동을 하는 상태에서 추가적인 기능을 넣고 싶음
    // 또는 기능을 변경을 하고 싶음(일부분)
    // 자식이 부모를 선택하는 것(자식(더 많은 기능을 구현할거 + 부모))

        @Override
        int sum3 (int num1, int num2){

            return num1+num2;
        }

}
