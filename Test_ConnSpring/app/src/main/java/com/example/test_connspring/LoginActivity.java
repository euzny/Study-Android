package com.example.test_connspring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LoginActivity extends AppCompatActivity {
    EditText edt_id,edt_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WMemberDTO dto = new WMemberDTO();
                Gson gson = new Gson();
                dto.setId(edt_id.getText().toString());
                dto.setPw(edt_pw.getText().toString());
                AskTask task = new AskTask("and5",gson.toJson(dto));
                try {
                    InputStream in = task.execute().get(5000, TimeUnit.MILLISECONDS);
                    dto = gson.fromJson(new InputStreamReader(in),WMemberDTO.class);

                    CommonVal.dto = dto; //어느 클래스(액티비티 , Fragment)에서 접근을 하든 중간에 데이터를 비우지 않는 이상은 로그인 정보가 유지됨 (프로그램 종료 전까지)
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}