package com.example.ex10_vendingmachine2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        ArrayList<VendingDTO> resultlist = (ArrayList<VendingDTO>) intent.getSerializableExtra("resultlist");
        int money = intent.getIntExtra("money",0);


    }
}