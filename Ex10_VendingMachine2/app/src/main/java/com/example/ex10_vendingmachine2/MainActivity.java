package com.example.ex10_vendingmachine2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_usermoney;
    Button btn_result, btn_money;
    TextView tv_money;
    VendingDAO dao = new VendingDAO();
    ArrayList<VendingDTO> list;
    ArrayList<VendingDTO> resultlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_usermoney = findViewById(R.id.edt_usermoney);
        btn_result = findViewById(R.id.btn_result);
        btn_money= findViewById(R.id.btn_money);
        tv_money = findViewById(R.id.tv_money);

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //나중에 예외처리하면 됨 => 현 상태에서는 숫자만 넣고 테스트
                dao.money = Integer.parseInt(edt_usermoney.getText()+"");
                tv_money.setText("잔액:"+dao.money+"원");
            }
        });
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("resultlist",resultlist);   //사용자가 뽑은 음료 갯수 목록
                intent.putExtra("money",dao.money); //사용자가 반환 받아야할 금액
                startActivity(intent);
            }
        });
        //VendingDTO 위젯묶음하나(콜라, 콜라갯수, 콜라버튼) 컨트롤 해보기
        //더 긴 코딩으로 VendingDTO를 사용하는 방법
  /*      TextView tv_drinkname1 =findViewById(R.id.tv_drinkname1);
        TextView tv_drinkcnt1 = findViewById(R.id.tv_drinkcnt1);
        Button btn_order1= findViewById(R.id.btn_order1);*/
        VendingDTO dto = new VendingDTO("콜라",1000,10, findViewById(R.id.tv_drinkname1),
                findViewById(R.id.tv_drinkcnt1),findViewById(R.id.btn_order1));
        list =new ArrayList<>();
        list.add(dto);
        list.add(new VendingDTO("사이다",700,12, findViewById(R.id.tv_drinkname2),
                        findViewById(R.id.tv_drinkcnt2),findViewById(R.id.btn_order2)) );
        list.add(new VendingDTO("환타",800,5, findViewById(R.id.tv_drinkname3),
                        findViewById(R.id.tv_drinkcnt3),findViewById(R.id.btn_order3)));
        list.add(new VendingDTO("데미소다",900,10, findViewById(R.id.tv_drinkname4),
                        findViewById(R.id.tv_drinkcnt4),findViewById(R.id.btn_order4)));
        //DTO 라는 객체에 데이터에 사용할 변수 + 위젯(객체)를 하나로 묶어줌
        for (int i = 0; i<list.size(); i++){
            list.get(i).btn_order.setOnClickListener(this);
        }


    } //onCreate <= 메소드 안쪽에서 만든 모든 변수들은 메소드가 끝나는 블럭킹에서 메모리에서 사라진다.
    //(= 블럭킹 밖에 사용 불가 x = 지역 변수)

    //구현부를 내가 가지고 있는 클래스 바디(지역)로 가지고 옴
    @Override
    public void onClick(View v) {
            for (int i=0; i<list.size(); i++){
                if(v.getId() == list.get(i).btn_order.getId()){
                    //내가 원하는 index에 있는 DTO를 빼오는 작업을 할 수 가 있음
                    // 클릭한 button id와 내가 가지고 있는 button의 id가 일치한다면
                    if(dao.isCheckVending(list.get(i))){
                        resultlist.add(list.get(i));  //사용자가 버튼을 클릭할때마다 그 처리가 정상적으로 되었다면 누적시키기
                        dao.setMinus(list.get(i));
                        tv_money.setText("잔액: "+dao.money+"원");
                    }
                }

            }
    }
}