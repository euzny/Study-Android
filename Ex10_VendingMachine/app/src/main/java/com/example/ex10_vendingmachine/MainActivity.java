package com.example.ex10_vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_input, btn_return, bo_coke, bo_fan, bo_mou, bo_cider;
    EditText cash;
    TextView left_price, left_coke, left_fan, left_mou, left_cider;
    int money=0;  //금액
    ArrayList<DrinkDTO> drinklist = new ArrayList<>();  //음료 목록
    ArrayList<DrinkDTO> buylist = new ArrayList<>();  // 주문한 음료 목록

    DrinkDAO dao = new DrinkDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bo_coke = findViewById(R.id.bo1);                       //콜라 주문 버튼
        bo_fan = findViewById(R.id.bo2);                       //환타 주문 버튼
        bo_mou = findViewById(R.id.bo3);                       //마운틴듀 주문 버튼
        bo_cider = findViewById(R.id.bo4);                       //사이다 주문 버튼

        btn_input = findViewById(R.id.btn_input);         //금액 입력 버튼
        btn_return=findViewById(R.id.btn_return);        //잔돈 반환 버튼
        cash=findViewById(R.id.cash);                   //입력한 금액
        left_price=findViewById(R.id.left_price);       //잔액

        left_coke = findViewById(R.id.left_bo1);        //남은 콜라 수량
        left_fan = findViewById(R.id.left_bo2);         //남은 환타 수량
        left_mou = findViewById(R.id.left_bo3);         //남은 마운틴듀 수량
        left_cider = findViewById(R.id.left_bo4);       //남은 사이다 수량

        drinklist.add(new DrinkDTO("콜라",800,9));
        drinklist.add(new DrinkDTO("환타",800,9));
        drinklist.add(new DrinkDTO("마운틴튜",700,8));
        drinklist.add(new DrinkDTO("사이다",800,7));



        left_coke.setText(drinklist.get(0).getCnt()+"개 남음");
        left_fan.setText(drinklist.get(1).getCnt()+"개 남음");
        left_mou.setText(drinklist.get(2).getCnt()+"개 남음");
        left_cider.setText(drinklist.get(3).getCnt()+"개 남음");



        // 금액 입력 버튼 클릭 시 잔액 확인
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money = dao.checkInt(cash.getText()+""); //int 타입 확인
                left_price.setText("잔액: "+money +"원");
                buylist.clear();  // 금액 새로 입력했을 때 장바구니 초기화
            }
        });

        //콜라 주문
        bo_coke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = dao.left_SM(drinklist.get(0),money,buylist);
                if(check == 1 || check == 2){
                    message(check); //토스트 메시지 출력
                } else{
                    money = check;
                    left_coke.setText(drinklist.get(0).getCnt()+"개 남음");
                    left_price.setText("잔액: "+money +"원");
                }
            }
        });

        //환타 주문
        bo_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = dao.left_SM(drinklist.get(1),money,buylist);
                if(check == 1 || check == 2){
                    message(check); //토스트 메시지 출력
                } else{
                    money = check;
                    left_fan.setText(drinklist.get(1).getCnt()+"개 남음");
                    left_price.setText("잔액: "+money +"원");
                }
            }
        });
        // 마운틴듀 주문
        bo_mou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = dao.left_SM(drinklist.get(2),money,buylist);
                if(check == 1 || check == 2){
                    message(check); //토스트 메시지 출력
                } else{
                    money = check;
                    left_mou.setText(drinklist.get(2).getCnt()+"개 남음");
                    left_price.setText("잔액: "+money +"원");
                }
            }
        });

        // 사이다 주문
        bo_cider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = dao.left_SM(drinklist.get(3),money,buylist);
                if(check == 1 || check == 2){
                    message(check); //토스트 메시지 출력
                } else{
                    money = check;
                    left_cider.setText(drinklist.get(3).getCnt()+"개 남음");
                    left_price.setText("잔액: "+money +"원");
                }
            }
        });

        //잔돈반환 버튼 클릭시 화면 이동
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("buylist", buylist);
                intent.putExtra("money", money);
                startActivity(intent);
                cash.setText("");
                left_price.setText("");
                money = 0;
            }
        });
    }

    public void message(int check){   //토스트 메시지 출력
        if(check == 1){
            Toast.makeText(MainActivity.this,"잔액이 부족합니다.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"수량이 부족합니다.",Toast.LENGTH_SHORT).show();
        }
    }

}