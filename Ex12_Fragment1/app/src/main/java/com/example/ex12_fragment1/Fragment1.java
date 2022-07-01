package com.example.ex12_fragment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {
    //1. 프레그먼트를 생성하고나서 new => Fragment(Blank) => onCreateView 이외에는 다 필요 없음 지움


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);
        rootView.findViewById(R.id.btn_frag1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("frag", "onClick: 프래그먼트 버튼 클릭됨");
            }
        });
        return rootView ;
    }
}