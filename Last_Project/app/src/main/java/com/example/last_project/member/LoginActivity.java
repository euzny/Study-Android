package com.example.last_project.member;

import androidx.appcompat.app.AppCompatActivity;

import com.example.last_project.MainActivity;
import com.example.last_project.R;
import com.example.last_project.common.AskTask;
import com.example.last_project.common.CommonMethod;
import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.User;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.oauth.view.NidOAuthLoginButton;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfileResponse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    EditText edt_id, edt_pw;
    Button btn_login,btn_join;
    CheckBox chk_auto;
    final String TAG ="login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //NaverIdLoginSDK.initialize(context, {OAUTH_CLIENT_ID}, {OAUTH_CLIENT_SECRET}, {OAUTH_CLIENT_NAME})
        NaverIdLoginSDK.INSTANCE.initialize(this,"KaVQyDcNh_pMjCBVrqLG","PXfblqZYGB","lastproject");
        KakaoSdk.init(this,"c006f031712cfaf72e9114ab242a0b4c");
        getHashKey();
        NidOAuthLoginButton btn_naver = findViewById(R.id.btn_naver);
        ImageView btn_kakao = findViewById(R.id.btn_kakao);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    Log.d(TAG, "카카오 토큰이 있음.로그인 정보를 빼오면 됨");
                    getKaKaoProfile();
                }else{
                    Log.d(TAG, "카카오 토큰이 없음"+throwable.toString());
                }
                return null;
            }
        };

        btn_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    Log.d("login", "카톡 설치");
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                }else{
                    Log.d("login", "카톡 미설치");
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,callback);
                }
            }
        });

        btn_naver.setOAuthLoginCallback(new OAuthLoginCallback() {
            @Override
            public void onSuccess() {
                Log.d("login", "onSuccess: "+  NaverIdLoginSDK.INSTANCE.getAccessToken());
                Log.d("login", "onSuccess: "+  NaverIdLoginSDK.INSTANCE.getRefreshToken());
                getNaverProfile();
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("login", "onFailure: "+s);
            }

            @Override
            public void onError(int i, String s) {
                Log.d("login", "onError: "+s);
            }
        });

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login =findViewById(R.id.btn_login);
        btn_join = findViewById(R.id.btn_join);

        chk_auto = findViewById(R.id.chk_auto);
        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        edt_id.setText(preferences.getString("id" , ""));
        edt_pw.setText(preferences.getString("pw" , ""));


        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_id.getText().toString().trim().length() <1 ||  edt_pw.getText().toString().trim().length() < 1){
                    Toast.makeText(LoginActivity.this, "아이디 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    MemberDAO dao = new MemberDAO(LoginActivity.this);
                    if(dao.isMemberLogin()){
                        checkAutoLogin();
                        goMain(); //Intent 이용해서 메인 액티비티로 이동
                    }else{
                        chk_auto.setChecked(false);
                        checkAutoLogin();
                    }
                }
            }
        });
        if(edt_id.getText().toString().length()>=1){
            chk_auto.setChecked(true);
            btn_login.performClick();
        }
    }

    public void goMain(){       //카카오, 네이버, 일반로그인 했을때 메인액티비티로 이동하게 로그인 처리
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void checkAutoLogin(){
        //해당하는 화면 외에는 공유를 안하는 경우.
        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        //공유자원을 사용하는 방법 1.생성 ↑
        //공유자원을 수정할수있는 객체가 별도로 존재함.
        SharedPreferences.Editor editor = preferences.edit();
        if(chk_auto.isChecked()){
            editor.putString("id" , edt_id.getText().toString()); //test라는 key(name)을 구분자로 두고 data라는 값을 넣음.
            editor.putString("pw" , edt_pw.getText().toString()); //test라는 key(name)을 구분자로 두고 data라는 값을 넣음.

        }else{
            //editor.remove("id");
            editor.clear(); //<=전체지우기.
        }
        editor.apply();
    }

    public void getNaverProfile(){ //<= 액세스 토큰이 있을때만 성공함
        NidOAuthLogin nidOAuthLogin = new NidOAuthLogin();
        nidOAuthLogin.callProfileApi(new NidProfileCallback<NidProfileResponse>() {
            @Override
            public void onSuccess(NidProfileResponse nidProfileResponse) {
                Log.d("login", "onSuccess: "+nidProfileResponse.getProfile().getEmail());
                Log.d("login", "onSuccess: "+nidProfileResponse.getProfile().getId());
                Log.d("login", "onSuccess: "+nidProfileResponse.getProfile().getName());
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("login", "onFailure: "+s);
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
    public void getKaKaoProfile() {
        //사용자 정보 요청(기본)
        UserApiClient.getInstance().me((user, throwable) -> {
            if (throwable != null) {
                Log.d(TAG, "사용자 정보 요청 실패" + throwable.toString());
                //오류가 났을때는 어떤 오류인지를 KaKao에서 제공해줌. KOE + 숫자
            } else {
                //[ { } ] json 구조처럼 바로 데이터가 있는게 아니라 Account라는 키로 한칸을 들어감(오브젝트)
                //그 안에서 profile을 얻어 올 수가 있음.
                Account account = user.getKakaoAccount();
                if (account != null) {
                    Log.d(TAG, "사용자 정보요청 성공" + account.getProfile());
                    Log.d(TAG, "사용자 정보요청 성공" + account.getEmail());
                }
            }
            return null;
        });
    }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}