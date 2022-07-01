package com.example.ex05_examplejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
//어떤 위젯이든 변수형태로 담아서 사용한다.  
//  find를 통해서 xml에 미리 디자인 해놓은 위젯을 찾아서 연결시킴
// 클래스 '사이에' 변수를 만들면 전역변수가 됨 (멤버)
    EditText edt_text1, edt_text2;
    Button btn_plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_text1 = findViewById(R.id.edt_text1);
        edt_text2 = findViewById(R.id.edt_text2);
        btn_plus = findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메소드 호출 규칙
                // 메소드의 정의 부분 즉 이름까지를 써줌
                // 메소드 정의부에 파라미터가 있다면 파라미터에 담을 데이터를 넘겨줌
                // 정의와 호출부를 합치게 되면 변수 초기화 식이 된다.

                int num1 = rtnInt(edt_text1.getText()+"");
                int num2 = rtnInt(edt_text2.getText()+"");
                Log.d("plusSum", ""+(num1+num2));
            }
        });
    }
    //Method
    // 1. 메소드는 메소드 블럭킹 밖에서 만듦 (클래스 안에 있어야함)
    //2. void 냐 void 아니냐 (return 이 필요한 경우, return 이 필요없는 경우)
    //3. public (전체) default(생략) - 같은 패키지, private -클래스까지

    int rtnInt(String str1){
        try {
            int num = Integer.parseInt(str1);
            return num;
        }catch (Exception e){
            return 0;
        }
    } //int
}
//버튼을 클릭했을때 edt1,edt2 에 들어있는 숫자의 합을 log에 찍는 프로그램을 완성하시오
// 사용자는 숫자 외에 값을 입력할 가능성이 있다. (잘못된 입력입니다. 숫자만 입력가능)
// 사용자는 아무 값도 입력 안할수도 있음 (값을 입력하세요)
//EditText가 return 하는 데이터는 숫자가 아님
//edt_text1.getText(); // EditText에 있는 글자를 가지고옴
// Log.d("plus", 123+456+"");  //579
//Log.d("plus", ""+123+456);  //123456
//WrapClass <= 정수형 변수들은 기본적으로 Parsing을 위한 클래스를 가지고 잇음
//포장지(랩핑) 할 수 있는 클래스
//double = Double , flot = Float, int = Integer
//스태틱 메소드를 제공함 (인스턴스화 new x) 바로 사용가능한 메소드
//int + int = int
//int + String + int = String
//개발자가 의도한대로 사용자가 프로그램을 사용하지 않았을때 오류가 나옴(하드웨어 x , 프로그램적 오류 -> 개발자가 핸들링 가능
//Exception (예외)
//예외(오류)가 나올것같은 코드를 실행부에 놓고 코드가 실행했을때
// 오류가 난다면 오류처리를 위한 블럭킹을 만들어 예외처리를 하는것
//try {실행코드}catch(Exception e){오류코드}