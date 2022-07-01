package com.example.ex14_practice.join;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ex14_practice.R;


public class JoinFrag2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button btn_second;

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_join_frag2, container, false);
        btn_second = viewGroup.findViewById(R.id.btn_second);
        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    JoinActivity joins = (JoinActivity) getActivity();
                    //joins.getSupportFragmentManager().beginTransaction().replace(R.id.container, new JoinResultFragment()).commit();
                joins.changeFragment(new JoinResultFragment());
            }
        });
        return viewGroup;
    }
}