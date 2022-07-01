package com.example.ex14_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SubActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        Intent intent =getIntent();   //Main에서 보내준 Intent를 받아옴

        ImageView imgv1 = findViewById(R.id.imgv1);
        //Tag <= 어떠한 속성을 넣어놓고 나서 해당하는 속성을 이용하는 기법 (Object 형태를 넣기 때문에 대부분의 것은 넣어짐)
        imgv1.setTag(1);   //초기에 1을 넣어둠.

        imgv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("태그가 있는 값", imgv1.getTag().toString());
                if(imgv1.getTag().toString().equals("1")){
                    imgv1.setImageResource(R.drawable.ic_launcher_background);
                    imgv1.setTag(2);

                }else{
                    imgv1.setImageResource(R.drawable.ic_launcher_foreground);
                    imgv1.setTag(1);
                }
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SubActivity1.this, FragAct.class);
                startActivity(intent1);
            }
        });
    }
}