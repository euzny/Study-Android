package com.example.ex11_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    //onCreate 1. 액티비티가 렌더링(화면에 보이는 단계 중 가장 먼저 실행됨)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        Log.d("lifecycleA", "onCreate: 1단계");
    }

    //2. 단계
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycleA", "onStart: 2단계");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lifecycleA", "onClick: 클릭됨");
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);

            }
        });
    }
    //1.Loader(코딩) => 2.Parsing(변환)=>3.렌더링트리 
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycleA", "onResume: 3단계");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycleA", "onResume: 액티비티가 다시 시작됨");
       button.setText("액티비티 다시 실행");
       //새로운 화면을 띄우고 나서 새 화면이 종료되고 어떤 처리가 필요한 경우
    }

    //액티비티가 종료되기전 일시정지를 함 (종료 1단계)
    // 액티비티가 새로운 화면을 띄우기 전에 일시정지를 함 (정지 1단계)

    @Override
    protected void onPause() {
        super.onPause();
    }
    // 액티비티가 종료 되기전 정지를 시킴 (종료 2단계)
    // 액티비티가 새로운 화면을 띄우기 전에 정지를 함 (정지 2단계) -끝- => 새 화면 띄움
    @Override
    protected void onStop() {
        super.onStop();
    }

    // 액티비티가 종료 되기전 마지막 단계 (종료 3단계) -> 종료
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}