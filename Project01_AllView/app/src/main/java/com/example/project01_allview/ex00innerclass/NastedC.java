package com.example.project01_allview.ex00innerclass;

import android.util.Log;

public class NastedC {
        //멤버  : 인스턴스 멤버(instance) , 스테틱 멤버(static)
        // 인스턴스 멤버 : 해당하는 클래스의 내부에 있으며 반드시 인스턴스화 과정(new 클래스이름)
        //거쳐야만 접근을 할수있는 멤버, 인스턴스화 된 변수에 .(점)을 찍으면 나오는 멤버

        //스테틱 멤버 : 해당하는 클래스의 내부에 있으며 해당하는 클래스가 인스턴스화 과정을 거치지 않아도 해당하는 클래스에 .(점)을 찍으면 나오는 멤버

        // { <= 메모리에 올림 } <= 메모리에서 내림
        // 자바는 기본적으로 생성자를 만들지 않으면 빈생성자 클래스이름(){} <- 제공해줌
        int field; //NastedC라는 클래스의 필드(멤버) static 여부에 따라서 멤버를 구분할 수 있다.
        void method(){}

        //생성자 '메소드 오버로딩' = 메소드의 이름이 '같고' 입력받는 파라메터의 타입 또는 갯수가 다를때
        //중첩해서 같은 이름으로 만든 후, 내가 필요한 메소드를 선택해서 사용하게 만드는 것.
        public NastedC(){

        }

        public NastedC(int field) {
                this.field = field;
        }

        static  String field2;
        static void method2(){ }
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------//
        public class A {   //인스턴스 멤버, NastedC의 인스턴스 클래스
                String field; // <= 최상위 NastedC => A 클래스 => A 라는 클래스의 인스턴스 멤버임
               // static String field2; 프로그램 시작하자마자 메모리에 있어야함
                //1. NastedC라는 클래스가 메모리에 올라가야함(new)
                //2. NastedC를 통해서 A라는 클래스를 또 메모리에 올려야하기 때문에 (new) static 사용 불가
                void method1(){

                }
        } //class A
        public static class B{
                String bField;
                static String bFieldStatic;
                void method1(){

                }
                static  void method2(){

                }
                //1. NastedMain에서 멤버클래스인 B를 생성하기
                //2. B의 모든 멤버에 접근하기
                //3.  A에서는 static 키워드를 사용할수가없었음 멤버클래스 B에서는 왜 사용이 가능할까?
        }

        // 메소드 안 쪽에서 만들어진 모든 것들은 지역변수
        public void methodClass(){
                int aa = 0;
                class  D{
                        //static String aaa="";
                        int  dField = 30;
                        void methodD(){
                                Log.d("메소드 D", "methodD: 접근");
                        }
                }
                aa = 30;
                D d = new D();
                d.dField = 40;
                d.methodD();
                //D라는 클래스에 있는 dField 와 methodD 사용해보기
        }
} //NastedC
