package com.example.ex11_backgroundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    //0. 컴포넌트들은 반드시 AndroidManifest.xml에 등록이 되어야한다.

    //1. 초기에 프로젝트를 만들게 되면 기본적으로 MainActivity 자바를 제공해줌.
    //맨먼저 이 액티비티가 화면에 나오는 이유는 Manifest의 설정 때문임.
    //AndroidManifest.xml

    //SplashActivity <= 로고를 사용자에게 각인시켜서 브랜드의 이미지를 기억하게 하거나
    //또는 로고를 보여주는 시간동안 디비에 있는 데이터를 불러옴으로써 사용자에게 편리함을 주는 화면.
    //빈 activity 생성 -SplashActivity

    Button btn_alert;
    static ProgressBar pg_bar;
    int cnt =0;
    //boolean isDown = false;  -> 전역변수를 이용하는 방법
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_alert = findViewById(R.id.btn_alert);
        pg_bar = findViewById(R.id.pg_bar);
        //pg_bar.setProgress(50);


        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialog < = AlertDialog.Builder 만들 수 있는 설정들을 미리 지정을 해놓고 세팅함
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("다운로드 안내");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage("Service를 이용해서 다운로드 하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //서비스를 실행하는 방법 Intent (컴포넌트임)
                        Intent intent = new Intent(MainActivity.this, FileDownLoadService.class);
                        startService(intent);


                        //btn_alert.setEnabled(false); -> 버튼 자체를 못 누르게 함.

                        //예를 눌렀을때 처리 => 현재의 파일 다운로드 시작
                        // <= 다이어로그에서 예 버튼을 클릭했을때 반복문을 이용해서 1부터 100까지 progress가 바뀌게 처리.
                        //바뀌는 과정이 눈으로 보여야함.
                        //Thread.sleep <= 메인 (현재 돌아가고 있는 안드로이드 os 에 휴지 시간)
                        // 예 버튼을 클릭하면 Thread는 new를 통해 인스턴스화를 계속하고 메모리에 각각 올라가서
                        // 작업을 비동기로 여러개가 실행이 됨
                        //progress 가 진행중인 상태면 다시 버튼을 눌러도 Thread를 new로 생성 못하게 막기 
                        //===========================================================//

                        //if(pg_bar.getProgress() == 0  && pg_bar.getProgress() == 100){
                /*      if(cnt == 0) {
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        for (int i = 1; i <= 100; i++) {
                                           cnt++;
                                            pg_bar.setProgress(i);
                                            Thread.sleep(100); // 현재 프로세스에 휴지시간을 줌 1000 = 1초, 0.1초
                                        }
                                        //btn_alert.setEnabled(true);
                                        cnt = 0;
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }  //run
                            });
                            thread.start();
                        //Log.d("name", thread.getName());
                     }*/
                        //===========================================================// 작업중일때는 실행 못하게 막기
                    }  //onClick
                }); // builder.setPositiveButton

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }); // builder.setNegativeButton

               /* builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });*/
                AlertDialog dialog = builder.create(); // <= create()라는 메소드는 AlertDialog 를 return
                dialog.show();
            }

        });
    }  //onCreate
}