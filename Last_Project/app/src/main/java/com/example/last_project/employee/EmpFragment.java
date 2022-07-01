package com.example.last_project.employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.last_project.R;
import com.example.last_project.common.AskTask;
import com.example.last_project.common.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class EmpFragment extends Fragment {
    ArrayList<EmployeeVO> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // ^ 네트워크 오류를 풀어준다 ^
        // excute();  // 어싱크 실행시 비동기 작업
        // excute().get() // 어싱크 실행 시 동기로 작업함(완료 될때까지 프로그램이 대기상태)
        View view = inflater.inflate(R.layout.fragment_emp, container, false);
        RecyclerView rec_emp = view.findViewById(R.id.rec_emp);
        list = selectList();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        EmpAdapter adapter = new EmpAdapter(inflater,list);
        rec_emp.setAdapter(adapter);
        rec_emp.setLayoutManager(manager);
        return view;
    }

    public ArrayList<EmployeeVO> selectList(){
        Gson gson = new Gson();
        AskTask task = new AskTask("list.em");
        InputStreamReader ir = CommonMethod.executeAskGet(task);
        ArrayList<EmployeeVO> list = null;
        if(ir != null){
            Log.d("ttt", "selectList: 널이 아님");
            list = gson.fromJson(ir, new TypeToken<ArrayList<EmployeeVO>>(){}.getType());
        }else{
            Log.d("ttt", "selectList: 널 임");
        }
        return list;
    }
}