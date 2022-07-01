package com.example.ex00_practice_methodclass;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Examplee {
    //1. 반드시 인스턴스화 해야지만 호출할수 있는 배열변수를 만들고 호출해보기
    public int [] array;
    //2. 인스턴스화 과정없이 사용할수 있는 ArrayList<String>을 만들고 접근해서 사용해보기
    static ArrayList<String> str ;
    //3. 1번과 같은 멤버인 메소드를 만드시오 return 타입은 Button
    public Button rbtn (){
        return null;
    }
    //4. 2번과 같은 메소드를 만드시오 return 타입은 EditText
    static EditText redt(){
        return null;
    }
}
