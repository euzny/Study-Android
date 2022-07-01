package com.example.project01_allview.ex02selflistview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project01_allview.R;

import java.util.ArrayList;

public class MusicFragment extends Fragment {
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_music, container, false);
        listView = viewGroup.findViewById(R.id.listview);
        ArrayList<MusicDTO> list = new ArrayList<>();
        MusicAdapter adapter = new MusicAdapter(list,inflater);
        list.add(new MusicDTO(R.drawable.optimize,"TOMBOY","(여자)아이들","I NEVER DIE","75,645"));
        list.add(new MusicDTO(R.drawable.optimize1,"GANADARA","박재범","GANADARA","72,130"));
        list.add(new MusicDTO(R.drawable.optimize2,"Feel My Rhythm","레드벨벳","The Reve Fest...","46,652"));
        list.add(new MusicDTO(R.drawable.optimize3,"사랑은 늘 도망가","임영웅","신사와 아가씨","140,970"));
        list.add(new MusicDTO(R.drawable.optimize4,"INVU","태연","INVU-The 3rd Album","140,970"));
        listView.setAdapter(adapter);

        return viewGroup;
    }
}