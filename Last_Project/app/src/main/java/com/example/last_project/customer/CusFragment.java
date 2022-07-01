package com.example.last_project.customer;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.L;
import com.example.last_project.R;
import com.example.last_project.common.AskTask;
import com.example.last_project.common.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CusFragment extends Fragment {
    RecyclerView recv_cus;
    SwipeRefreshLayout swipe;
    SearchView scv_cus;
    ArrayList<CustomerDTO> list;
    CusAdapter adapter;
    final private String TAG = "customer";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cus, container, false);
        swipe = view.findViewById(R.id.swipe);
        recv_cus = view.findViewById(R.id.recv_cus);
        scv_cus = view.findViewById(R.id.scv_cus);
        list =selectList();  //getContext <= 프래그먼트가  띄워져있는 부모가 되는 액티비티
        adapter = new CusAdapter(inflater,list,getContext());
        recv_cus.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
       recv_cus.setLayoutManager(manager);
       
       scv_cus.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          
           @Override  //돋보기 확인 버튼을 눌렀을때 처리
           public boolean onQueryTextSubmit(String query) {
               Log.d(TAG, "onQueryTextSubmit: "+query);
               // query라는 스트링 변수를 이용해서 해당하는 내용으로 customer 테이블에서 email 또는 이름 또는 전화번호가 해당하는 값과 일치하는 검색 정보 조회
               //SelectList <= 메소드 이용해서 처리
             //  selectList(query);
              adapter.setList(selectList(query));
              adapter.notifyDataSetChanged();
               return false;
           }

           @Override //글자가 바뀔때마다 처리
           public boolean onQueryTextChange(String newText) {
               if(newText == null || newText.length() <1){
                   refreshSelect();
               }
               Log.d("태그", "onQueryTextChange: "+newText);
               return false;
           }
       });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSelect();
                //여기 부분 실행전에 Spring에서 데이터 얻어오는 처리 넣어주면 됨.
                swipe.setRefreshing(false);
            }
        });
        return view;
    }
    public  ArrayList<CustomerDTO> selectList(){
        Gson gson = new Gson();
        AskTask task = new AskTask("list.cu");
        InputStreamReader ir = CommonMethod.executeAskGet(task);
        ArrayList<CustomerDTO> list = null;
        if(ir != null){
            list = gson.fromJson(ir,
                    new TypeToken<List<CustomerDTO>>(){}.getType());

        }else{
            Log.d(TAG, "selectList: 널 임");
        }
        return list;
    }

    public  ArrayList<CustomerDTO> selectList(String query){
        Gson gson = new Gson();
        AskTask task = new AskTask("list.cu");
        task.addParam("query",query);
        InputStreamReader ir = CommonMethod.executeAskGet(task);
        ArrayList<CustomerDTO> list = null;
        if(ir != null){
           list = gson.fromJson(ir,
                    new TypeToken<List<CustomerDTO>>(){}.getType());

        }else{
            Log.d(TAG, "selectList: 널 임");
        }
        return list;
    }

    @Override           //프래그먼트가 다시 작동할때마다 실행되는 부분
    public void onResume() {
        super.onResume();
        Log.d("태그", "onResume: "+"작동함");
        refreshSelect();
    }

    public void refreshSelect(){
        adapter.setList(selectList());
        adapter.notifyDataSetChanged();
    }
}