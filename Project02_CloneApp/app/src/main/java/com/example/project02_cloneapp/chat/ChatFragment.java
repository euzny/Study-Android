package com.example.project02_cloneapp.chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project02_cloneapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewGroup(자식, layout 종류), extend View(부모 Layout + 위젯)
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView rec_chat = v.findViewById(R.id.rec_chat);
        ArrayList<ChatDTO> list = new ArrayList<>();
        //img_url, name, msg, date

        list.add(new ChatDTO("https://item.kakaocdn.net/do/e050ac97cc8de81ef44055d3f3692bfca88f7b2cbb72be0bdfff91ad65b168ab", "친구1", "안녕", "오후 03:15"));
        list.add(new ChatDTO("https://item.kakaocdn.net/do/e050ac97cc8de81ef44055d3f3692bfca88f7b2cbb72be0bdfff91ad65b168ab", "친구2", "안녕", "오후 03:15"));
        list.add(new ChatDTO("https://c.tenor.com/vxJjiiRh3CUAAAAd/%EC%B6%98%EC%8B%9D-%EC%B6%98%EC%8B%9D%EC%9D%B4.gif", "친구3", "안녕", "오후 03:15"));
        list.add(new ChatDTO("https://c.tenor.com/vxJjiiRh3CUAAAAd/%EC%B6%98%EC%8B%9D-%EC%B6%98%EC%8B%9D%EC%9D%B4.gif", "친구4", "안녕", "오후 03:15"));
        list.add(new ChatDTO("https://c.tenor.com/vxJjiiRh3CUAAAAd/%EC%B6%98%EC%8B%9D-%EC%B6%98%EC%8B%9D%EC%9D%B4.gif", "친구5", "안녕", "오후 03:15"));


        ChatAdapter adapter = new ChatAdapter(inflater, list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false
        );
        rec_chat.setAdapter(adapter);
        rec_chat.setLayoutManager(manager);
        return v;
    }
}
