package com.example.ex12_fragment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
        //1. 액티비티, 2. 서비스 <= M
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View 타입을 상속 받은 위젯들은 전부다 OnClickListener 가 있기 때문에 find 뒤에 써도 무방
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //프래그먼트를 붙이는 방법
                //Activity(getSupportFragmentManager <= 이용)
                //파라메터 중 @--res로 끝나는 것들은 res폴더에 있는 것
                //Transaction <= UPDATE, DELETE, INSERT  <= commit, rollback

                if(flag){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment1()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment2()).commit();
                }
                flag =!flag;
            }
        });
    }
}