package com.example.ex10_vendingmachine;

import android.widget.TextView;

import java.util.ArrayList;

public class DrinkDAO {

    //int 타입 확인
    public int checkInt(String cash) {
        try {

            return Integer.parseInt(cash);

        }catch (Exception e){
            return 0;
        }

    }

    //음료 재고 확인 & 잔액 확인
    public int left_SM (DrinkDTO dto, int money,ArrayList<DrinkDTO> buylist){
        if(dto.getCnt() > 0 ){
            if(money < dto.getPrice()){   //잔액 부족
                return 1;
            }else{                           //정상 계산
                dto.setCnt(dto.getCnt()-1);
                addlist(dto,buylist);   //주문 리스트에 음료 추가
                money -= dto.getPrice();  //잔액 계산
                return money;
            }
        }else{                  //재고 부족
            return 2;
        }
    }

    //주문 목록 추가
    public void addlist(DrinkDTO dto, ArrayList<DrinkDTO> buylist) {

        int flag = 0;  // 주문목록에 값이 없을때
        for (DrinkDTO d : buylist) {      //buylist 주문목록에 이름이 있으면 수량을 +1
            if (d.getName().equals(dto.getName())) {
                d.setCnt(d.getCnt() + 1);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {                    //주문 목록에 이름이 없을때 추가
            buylist.add(new DrinkDTO(dto.getName(), dto.getPrice(), 1));
        }
    }
}

