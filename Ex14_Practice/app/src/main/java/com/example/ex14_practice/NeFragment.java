package com.example.ex14_practice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //나중에 위젯을 찾아서 사용할 때는 return 타입인 view를 내가 원하는 변수에 담아서 사용
        View rootView = inflater.inflate(R.layout.fragment_ne, container, false);
        Button btn_back = rootView.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity 1순위는  2순위 간섭 가능
                // Fragment 2 순위  <서로 간섭 불가 > Fragment 2
                //액티비티는 new로 생성할 수 없음!!!!!!!!
                // FragAct act = new  FragAct();  <--------------- "불가능"
                FragAct act = (FragAct)  getActivity(); // <= 나를 담고 있는 액티비티를 받아와서 변수에 담아서 사용
                act.changeFragment();
            }
        });
        return rootView;


    }

}