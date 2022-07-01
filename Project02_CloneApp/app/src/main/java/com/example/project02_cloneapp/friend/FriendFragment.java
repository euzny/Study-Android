package com.example.project02_cloneapp.friend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project02_cloneapp.R;

import java.util.ArrayList;


public class FriendFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_friend, container, false);
        RecyclerView rec_frdlist= rootView.findViewById(R.id.rec_frdlist);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        ArrayList<FriendDTO> list = new ArrayList<>();
        list.add(new FriendDTO(R.drawable.eye,"홍길동","상태메시지"));
        list.add(new FriendDTO(R.drawable.etc,"친구1","!!!!!!!!"));
        list.add(new FriendDTO(R.drawable.talk,"친구2","&&&&&&&&&"));
        list.add(new FriendDTO(R.drawable.music,"친구3","^^"));
        list.add(new FriendDTO(R.drawable.search,"친구4","*******"));
        FriendAdapter adapter = new FriendAdapter(inflater,list,getContext());
        //Fragment 는 화면에 바로 떠있는게 아니라 액티비티 위에 떠있음. 따라서 액티비티에서 기능을 받아올 수 잇음
        //getContext, get Activitiy method 를 통해서
        //Activity에서의 context 는 내 클래스 자신을 뜻함 (MainActivity.this)
        rec_frdlist.setLayoutManager(manager);
        rec_frdlist.setAdapter(adapter);
        return rootView;
    }
}