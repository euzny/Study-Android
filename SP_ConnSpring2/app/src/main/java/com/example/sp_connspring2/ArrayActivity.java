package com.example.sp_connspring2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ArrayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);
        ArrayList<TestDTO> list = new ArrayList<>();
        for(int i= 0; i<=10; i++){
            list.add(new TestDTO("id"+i, "pw"+i));
        }//Spring (ArrayList) -> String(json) => AskTask(json) =>Spring
        Gson gson = new Gson();
        String data =  gson.toJson(list);
        Log.d("aaa", "onCreate: "+data);
        AskTask task = new AskTask("testlist",data);
        try {

            InputStream in =  task.execute().get(5000, TimeUnit.MILLISECONDS);
            list = gson.fromJson(new InputStreamReader(in),new TypeToken<List<TestDTO>>(){}.getType());
            for(int i=0; i<list.size(); i++){
                Log.d("tag", list.get(i).getId());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}