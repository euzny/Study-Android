package com.example.ex10_vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    String list="";
    TextView buy_drink;  //구매한 음료 목록
    TextView left_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        buy_drink = findViewById(R.id.buy_drink);
        left_money = findViewById(R.id.left_money);
        //음료 주문 목록
        ArrayList<DrinkDTO> buylist = (ArrayList<DrinkDTO>) getIntent().getSerializableExtra("buylist");
        /// 잔액
        int money = getIntent().getIntExtra("money",0);
        for(DrinkDTO d: buylist){
            list += (d.getName()+d.getCnt()+"개  ");
        }
        buy_drink.setText(list);
        left_money.setText("잔액: "+money+"");
    }

}