package com.example.project01_allview.ex04selfgridview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.project01_allview.R;

import java.util.ArrayList;

public class GridSelfFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_grid_self, container, false);
        GridView gridView = viewGroup.findViewById(R.id.gridweb);
        //framgent_grid_self 안에 listview 가 없다면 find를 통해서 찾아도 gridView는 null 상태

        ArrayList<WebDTO> list = new ArrayList<>();
        for(int i=0; i<=10; i++ ){
            list.add(new WebDTO("https://shared-comic.pstatic.net/thumb/webtoon/748105/thumbnail/thumbnail_IMAG10_becd3e24-cc09-4243-a1c9-646270f4a8db.jpg","독립일기","자까","9.97"));
        }

        WebAdapter adapter = new WebAdapter(list,inflater);
        gridView.setAdapter(adapter);

        return viewGroup;
    }
}