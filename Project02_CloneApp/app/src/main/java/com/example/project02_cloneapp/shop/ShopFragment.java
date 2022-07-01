package com.example.project02_cloneapp.shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project02_cloneapp.R;

import java.util.ArrayList;


public class ShopFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop, container, false);

        RecyclerView rec_shop = v.findViewById(R.id.rec_shop);

        ArrayList<ShopDTO> list = new ArrayList<>();
        list.add(new ShopDTO("https://img.mimint.co.kr/mintsale/product/2020/6/1/P20200601152517983.jpg","마스크","톡딜가 8,900원"));
        list.add(new ShopDTO("https://www.thinkfood.co.kr/news/photo/201805/80877_102851_2039.jpg","생수","톡딜가 5,400원"));
        list.add(new ShopDTO("http://www.medigatenews.com/file/news/183705","마스크","톡딜가 8,900원"));

        ShopAdapter adapter = new ShopAdapter(inflater,list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rec_shop.setAdapter(adapter);
        rec_shop.setLayoutManager(manager);

        return v;
    }
}