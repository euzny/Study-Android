package com.example.ex09_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    String intData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //Intent 만듦 new로 만드는 거 x -> MainActivity에서 보내준 것을 받아오게끔 처리를 해야함
        Intent intent = getIntent();  //MainActivity에서 물려놓은 Intent 를 받아올 수 있음 (Controller => jsp)
        String value = intent.getStringExtra("value1");  //name 은 대소문자 구분을 함
        int value2 = intent.getIntExtra("value2",0);  //null을 담을 수 없는 int 형 데이터는 key로 데이터를 찾았을때 없다면 담을 숫자

        //Toast.makeText(this, value+value2, Toast.LENGTH_SHORT).show();
         UserDTO dto = (UserDTO) intent.getSerializableExtra("dto");
        Toast.makeText(this, dto.getId()+dto.getAge(), Toast.LENGTH_SHORT).show();
        for(int i = 1; i<=10; i++){
            //int tost = intent.getIntExtra("num"+i,0);
            // Toast.makeText(this, tost+"", Toast.LENGTH_SHORT).show();
            intData += intent.getIntExtra("num"+i,0) + "";
        }
        Toast.makeText(this, intData, Toast.LENGTH_SHORT).show();
        ArrayList<UserDTO> dtoList = (ArrayList<UserDTO>)  intent.getSerializableExtra("list");
    }
}