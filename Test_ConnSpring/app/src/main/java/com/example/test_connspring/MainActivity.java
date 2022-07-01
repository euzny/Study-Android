package com.example.test_connspring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tx_id = findViewById(R.id.tx_id);
        TextView tx_pw = findViewById(R.id.tx_pw);
        if(CommonVal.dto != null){
            tx_id.setText("아이디:" +CommonVal.dto.getId());
            tx_pw.setText("비밀번호:"+CommonVal.dto.getPw());
        }else{
            Log.d("액티비티", "널 오류");
        }
        
    }
}