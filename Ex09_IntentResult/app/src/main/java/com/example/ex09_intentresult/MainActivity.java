package com.example.ex09_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        tv1 = findViewById(R.id.tv1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDTO dtoa;
                dtoa = new UserDTO("hong","aaaaa","길동",11);
                // 인텐트 (Intent) : 인텐트는 크게 두가지 종류로 구분
                // 암시적(묵시), 명시적
                // 명시적 인텐트, 정확한 위치를 알고 있을때(내가 소스를 가지고 있음) 사용
                // ex) 액티비티 메인(회원 가입) ==> (intent) => 액티비티 서브(로그인 화면으로 보내줄때) , 새 액티비티 띄우기
                // 암시적(묵시적 인텐트) 정확한 위치는 모르지만 어떠한 서비스를 실행할때 사용
                // ex ) 액티비티 메인 => 전화걸기(os), 문자보내기(os), 카메라로 사진찍기(os) , naver.com (internet) 사용하기

                Intent intent = new Intent(MainActivity.this, SubActivity.class);
               //intent 에 데이터를 담아서 넘긴다.
                //HashMap<String,String> map = new HashMap<>();
                //map.put("key","value");
               // intent.putExtra("value1","이 데이터는 Main에서 보냄");
               // intent.putExtra("value2",1000);
                UserDTO dto = new UserDTO("hong","aaaaa","길동",11);
                //new 를 통한 생성자 메소드는 객체 타입을 리턴한다.
                //배열 Array ====> List (=> ArrayList <- LinkedList - 느림)

                UserDTO[] arrs = new UserDTO[4];

                arrs[0] = new UserDTO("A","b","c",111);

                ArrayList<UserDTO> list = new ArrayList<>();
                for(int i= 0; i<10; i++){
                    list.add(new UserDTO("num"+i, "aaaa","이름",i));
                }
                intent.putExtra("list",list);

                intent.putExtra("dto",dto);
                //직렬화 된 객체가 아니면 Intent 를 통해서 넘길수가 없음.

                for(int i=1; i<=10; i++){
                        intent.putExtra("num"+i, i);
                }
                startActivity(intent);
            }


        });
    }
}