package com.example.project01_allview.ex03gridview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.project01_allview.R;
import com.example.project01_allview.ex02selflistview.MusicAdapter;
import com.example.project01_allview.ex02selflistview.MusicDTO;

import java.util.ArrayList;

public class GridFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_grid, container, false);
        GridView gridView = viewGroup.findViewById(R.id.gridview);

        ArrayList<MusicDTO> list = new ArrayList<>();
        MusicAdapter adapter = new MusicAdapter(list,inflater);
        for(int i = 0; i<= 20; i++){
            list.add(new MusicDTO(R.drawable.optimize,"TOMBOY","(여자)아이들","I NEVER DIE","75,645"));
        }


        gridView.setAdapter(adapter);
        return viewGroup;
    }
}