package com.example.ex11_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Log.d("lifecycleB", "onCreate: 1단계");

    }
    //2. 단계
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycleB", "onStart: 2단계");
    }
    //1.Loader(코딩) => 2.Parsing(변환)-=--==>43/..=>=>
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycleB", "onResume: 3단계");
    }
}