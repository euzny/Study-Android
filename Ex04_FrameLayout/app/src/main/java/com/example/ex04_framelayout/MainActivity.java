package com.example.ex04_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
//public <= 접근제한(어디서든 접근가능), private(클래스 내에서만), default(같은 패키지 내에서만 접근 가능)
// void이면 실행이 되고나서 return 없는 메소드 , void가 아니면 어떤 형태든 지정되어있는 DataType을 return 해야 하는 메소드 (null)
//Button, ImageView, Linearlayout, Framelayout

//Collection 자료구조, 배열 Array 어떤 것이든 내가 알고 있는 대부분은 자료구조형으로 바꿀 수 있음.
// ********************
/*    ArrayList<LinearLayout> list = null;
    FrameLayout[] fArr = new FrameLayout[5];

    Button rtnBtn(){
        return null;
    }
    ImageView rtnImageView(){
        return null;
    }*/
// lv  (local variable) <= 메소드 내에서 선언된 변수로 메소드 블럭킹 {} 사용할 수 없음
//iv (instance variable)<= Instance 객체를 new로 초기화해서 메모리에 올리는 방법 (인스턴스화 해야지만 다른 클래스에서 사용할 수 있는 변수)
//iv, sv = 멤버(클래스 블럭킹 안에서 사용할 수 있는 두개의 멤버)
//variable(변수선언), for(반복문), if(선택문),  class(오브젝트), method(함수), collection(ArrayList<>), oop(dao,dto)

public class MainActivity extends AppCompatActivity {
    int count = 0;
    ImageView imgv1, imgv2, imgv3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       imgv1 = findViewById(R.id.imgv1);
       imgv2 = findViewById(R.id.imgv2);
       imgv3 = findViewById(R.id.imgv3);
        Button btn1 = findViewById(R.id.btn1);  //이전 이미지
        Button btn2 = findViewById(R.id.btn2);  // 다음 이미지

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                changeImg();
            }
        });
      btn2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              count--;
              changeImg();

        }
      });
    }  //onCreate


 void changeImg(){
        if(count== -1) count = 2;
        if(count == 3) count = 0;

        imgv3.setVisibility(View.GONE);
        imgv2.setVisibility(View.GONE);
        imgv1.setVisibility(View.GONE);

        if(count==0){
            imgv2.setVisibility(View.VISIBLE);
        }else if(count==1){
            imgv1.setVisibility(View.VISIBLE);
        }else if(count==2){
            imgv3.setVisibility(View.VISIBLE);
        }
} //changeImg()

} //Class


