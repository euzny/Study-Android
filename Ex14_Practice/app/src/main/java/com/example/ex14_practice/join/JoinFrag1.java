package com.example.ex14_practice.join;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ex14_practice.R;

public class JoinFrag1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_join_frag1, container, false);
        Button btn_first = viewGroup.findViewById(R.id.btn_first);
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinActivity joins = (JoinActivity) getActivity();
               // joins.getSupportFragmentManager().beginTransaction().replace(R.id.container, new JoinFrag2()).commit();
                joins.changeFragment(new JoinFrag2());
            }
        });
        return viewGroup;
    }
}