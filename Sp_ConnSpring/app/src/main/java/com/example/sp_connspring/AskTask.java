package com.example.sp_connspring;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class AskTask extends AsyncTask<String, String, Integer> {

    //1. 파라미터...(doInBackground = 어떤 비동기 작업을 실제로 처리하는 부분)
    // 여기에 필요한 변수를 뜻한다.
    // <= 실사용에는 불편한점이 있어서 생성자를 이용하여 파라메터를 입력받을 수 있음
    //doInBackground(****String... strings****) ***<- 여기 안쪽

    //2. 프로그레스 ( 작업이 어느정도 진행되었는지를 다른 태스크(액티비티)에서 확인할 수 있는 변수를 뜻하는데
    // 정확도가 낮아서 신빙성이 없는 데이터이기 때문에 사용 안함.
    // Android => Spring에서의 작업 진행률을 확인할 수가 없음 => 정확 x

    //3. Result
    //작업을 완료하고 나서 어떤 데이터를 리턴받기 위한 타입
    // protected *Integer*

    final String HTPPIP ="http://192.168.0.34"; //ipconfig로 (cmd) ip를 확인하고 넣어주기
                                                                                    // 80 외에 포트는 : 포트번호 넣어주기

    final String SVRPATH = "/mid/";             //<-server.xml 에 기재된 path
                                                                        // HTTPIP + SVRPATH + Mapping 접속할 매핑주소(스프링)


    HttpClient httpClient; //접속을 위한 객체
    HttpPost httpPost; // 접속방식 - POST
    MultipartEntityBuilder builder; //파라미터, 파일 등등 (여러부분으로 나누어진) 보내기위한 객체
    private String postUrl ;  //HTTPIP + SVRPATH + Mapping 접속할 매핑주소(스프링)
    private String mapping ; // mapping부분은 매번 달라질 수 있기 때문에 객체 생성시 입력받아서  처리하게끔 필드로 만든다. 
    private String edt_data;
    private String edt_data2;

    public AskTask(String mapping, String edt_data, String edt_data2) {
        this.mapping = mapping;
        this.edt_data = edt_data;
        this.edt_data2 = edt_data2;
    }

    public AskTask(String mapping, String edt_data) {
        this.mapping = mapping;
        this.edt_data = edt_data;
    }

    public AskTask(String mapping) {
        this.mapping = mapping;
    }



    @Override
    protected Integer doInBackground(String... strings) {
        postUrl  = HTPPIP + SVRPATH + mapping; //http://192.168.0.34/mid/???
        builder = MultipartEntityBuilder.create(); //new MultiPart();  API28 미만은 안됌
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); //웹 형태로 요청 (Legacy)
        // =================== 파라미터를 추가할 부분 ==================
        builder.addTextBody("num1", edt_data, ContentType.create("Multipart/related","UTF-8"));
        builder.addTextBody("num2", edt_data2, ContentType.create("Multipart/related","UTF-8"));
        for(int i = Integer.parseInt(edt_data); i<=Integer.parseInt(edt_data2); i++ ){
            builder.addTextBody("param"+i, "And"+i,ContentType.create("Multipart/related","UTF-8"));
        }



        //==========================================================
        //json <= Gson <=

        httpClient = AndroidHttpClient.newInstance("Android");
        httpPost =new HttpPost(postUrl);//url연결을 이용해서 post 연결 객체 초기화

        httpPost.setEntity(builder.build()); //http 요청 모드를 builder 이용하여 입력

        try {
            //stream 흐름 프로그램 내부에서 밖으로 내보낼것인지, 외부에서 내부로 들여올것인지,
            // system.out.println <= out <= outputstream
            // scanner System.in  <= in <= inputStream
            InputStream in = httpClient.execute(httpPost).getEntity().getContent();  //실제 Spring으로 통신 시작
            rtnString(in);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
        }

        //Gson을 사용하는게 편하다는 것을 알기위한 테스트 단계
     //InputStream자체를 이용하게 되면 Stream을 편하게 읽기 위한 리더객체 필요함
    //StringBuilder <= 소스가 복잡해짐
    public String rtnString (InputStream in){
        BufferedReader reader =null;// in => isr => br
        try{
            reader=   new BufferedReader(new InputStreamReader(in,"UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                Log.d("TAG", "rtnString: " + line);
                //responseBody 응답을 받기 때문에
            }
        }catch (IOException e){

        }
        return null;
    }
}

//AskTask 는 extends를 해줘야만 인식을 한다. (해당하는 Class로)
//ASKTask 는 클래스타입 <>에 3개의 파라미터를 넘겨줘야하는 형태
