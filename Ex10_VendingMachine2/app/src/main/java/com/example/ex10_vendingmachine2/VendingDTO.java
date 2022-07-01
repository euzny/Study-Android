package com.example.ex10_vendingmachine2;

//자판기 프로그램에서 사용할 DTO
//DB와 매칭을 하지 않고 자체적으로 위젯들을 동적으로 사용하기 위한 객체
//TextView x2 , Button x1 <= 반복되는 패턴 (패턴 => 묶음 => list 형태)
//etc (이미지뷰까지 동적으로 바꾸고 싶을 때는 해당하는 위젯도 DTO에 넣는다)

import android.widget.Button;
import android.widget.TextView;

public class VendingDTO {
    String name; //음료의 이름
    int price; //음료의 가격
    int count ; //음료 수량
    TextView tv_name, tv_count;  //텍스트뷰(위젯) 화면에 음료 이름 (+가격),갯수(수량)
    Button btn_order;  //주문하기 버튼

    //메소드의 파라메터부(정의, 만든쪽) + 호출부(사용) = 변수 초기화식.※
    //void method(String a) <==> method("string값") = (String a = "string값");


    public VendingDTO(String name, int price, int count, TextView tv_name, TextView tv_count, Button btn_order) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.tv_name = tv_name;
        this.tv_count = tv_count;
        this.btn_order = btn_order;
        this.tv_name.setText(name+": "+price+"원");
        this.tv_count.setText(count+"개 남음");    }
}
