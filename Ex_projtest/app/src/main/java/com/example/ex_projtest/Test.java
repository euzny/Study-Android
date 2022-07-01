package com.example.ex_projtest;

import android.view.View;
import android.widget.ImageView;

public class Test {

    public void changeImg (ImageView imgvF, ImageView imgvS){

        if(imgvF.getVisibility()== View.VISIBLE){
            imgvF.setVisibility(View.GONE);
            imgvS.setVisibility(View.VISIBLE);
        }else{
            imgvF.setVisibility(View.VISIBLE);
            imgvS.setVisibility(View.GONE);
        }
    } //changeImg
}
