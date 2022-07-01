package com.example.ex_projtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    ImageView imgv1, imgv2, imgv3,imgv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);
        imgv4 = findViewById(R.id.imgv4);
        Test test = new Test();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    changimg(imgv2, imgv1);
            }
        }); //btn1

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changimg(imgv4, imgv3);
            }
        }); //btn2

    } //onCreate

    void changimg(ImageView imgvF, ImageView imgvS){

        if(imgvF.getVisibility()==View.VISIBLE){
            imgvF.setVisibility(View.GONE);
            imgvS.setVisibility(View.VISIBLE);
        }else{
            imgvF.setVisibility(View.VISIBLE);
            imgvS.setVisibility(View.GONE);
        }

    } //changeimg

} //MainActivity