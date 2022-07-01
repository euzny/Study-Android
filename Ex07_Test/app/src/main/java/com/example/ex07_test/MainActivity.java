package com.example.ex07_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener

{
    ImageView imgv1, imgv2, imgv3, imgv4;
    Button btn1, btn2;
    ArrayList<ImageView> imgList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);
        imgv4 = findViewById(R.id.imgv4);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        imgList.add(imgv1);
        imgList.add(imgv2);
        imgList.add(imgv3);
        imgList.add(imgv4);
        //new View.onClick == Interface(상속받아서 사용가능)
        //extends <= 상속이 하나만 됨
        // implements <= 다중 상속이 가능함. 강제로 만들어야할 메소드가 있음.
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn1){
                changeVis(imgv1,imgv2);
        }else if(v.getId() == R.id.btn2){
            changeVis(imgv3,imgv4);
        }
    } //OnClick (Interface를 상속받아서 구현부를 직접 클래스에 만듦)

    //이미지 뷰 갯수가 일정하지 않은 순간이 있음
    private void changeVis(ImageView imgv1, ImageView imgv2){
        if(imgv1.getVisibility()==View.VISIBLE){
            imgv1.setVisibility(View.GONE);
            imgv2.setVisibility(View.VISIBLE);
        }else{
            imgv2.setVisibility(View.GONE);
            imgv1.setVisibility(View.VISIBLE);
        }
    }
    int visible = 0;
    private void changeVis(ArrayList<ImageView> list){

        for(int i = 0; i < list.size(); i++){
                    list.get(i).setVisibility(View.GONE);
        }
        list.get(visible).setVisibility(View.VISIBLE);
        visible++;
    }
}