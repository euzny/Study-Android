package com.example.project01_allview.ex00innerclass;

public class NastedMain {

    public void main(){

        //NastedC의 멤버에 접근하기 (*) 추후에 ViewHolder라는 위젯을 묶어놓은 중첩클래스를 만듬
        //중첩클래스(클래스의 멤버가 클래스인것)
        NastedC nc = new NastedC();
        nc.field = 3;
        nc.method();
         nc = new NastedC(5);

         NastedC.field2="A";
         NastedC.method2();
         //=======================여기까지 해본 내용 ===================================//

        NastedC.A a = nc.new A();
        //1. NastedMain에서 멤버클래스인 B를 생성하기
        NastedC.B b= new NastedC.B();   // 스태틱 멤버이기 때문에 클래스에만 접근해도 접근가능
        //2. B의 모든 멤버에 접근하기
        b.bField = "String";
        NastedC.B.bFieldStatic="static"; //스태틱 클래스의 스태틱 멤버
        b.method1();
        NastedC.B.method2();

        //3.  A에서는 static 키워드를 사용할수가없었음 멤버클래스 B에서는 왜 사용이 가능할까?
        //클래스 A는 static으로 클래스를 선언하지 않아서 상위 클래스인 NastedC가 생성된 후에 A 클래스를 생성할 수 있어서
        //static으로 변수를 선언할 수 없으나
        //클래스 B는 static으로 먼저 선언을 했기때문에 메모리에 올라가 있어 사용이 가능하다.


    }
}
