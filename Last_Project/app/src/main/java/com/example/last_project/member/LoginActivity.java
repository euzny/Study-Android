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
                    Log.d(TAG, "????????? ????????? ??????.????????? ????????? ????????? ???");
                    getKaKaoProfile();
                }else{
                    Log.d(TAG, "????????? ????????? ??????"+throwable.toString());
                }
                return null;
            }
        };

        btn_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    Log.d("login", "?????? ??????");
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                }else{
                    Log.d("login", "?????? ?????????");
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
                    Toast.makeText(LoginActivity.this, "????????? ??????????????? ???????????????", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    MemberDAO dao = new MemberDAO(LoginActivity.this);
                    if(dao.isMemberLogin()){
                        checkAutoLogin();
                        goMain(); //Intent ???????????? ?????? ??????????????? ??????
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

    public void goMain(){       //?????????, ?????????, ??????????????? ????????? ????????????????????? ???????????? ????????? ??????
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void checkAutoLogin(){
        //???????????? ?????? ????????? ????????? ????????? ??????.
        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        //??????????????? ???????????? ?????? 1.?????? ???
        //??????????????? ?????????????????? ????????? ????????? ?????????.
        SharedPreferences.Editor editor = preferences.edit();
        if(chk_auto.isChecked()){
            editor.putString("id" , edt_id.getText().toString()); //test?????? key(name)??? ???????????? ?????? data?????? ?????? ??????.
            editor.putString("pw" , edt_pw.getText().toString()); //test?????? key(name)??? ???????????? ?????? data?????? ?????? ??????.

        }else{
            //editor.remove("id");
            editor.clear(); //<=???????????????.
        }
        editor.apply();
    }

    public void getNaverProfile(){ //<= ????????? ????????? ???????????? ?????????
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
        //????????? ?????? ??????(??????)
        UserApiClient.getInstance().me((user, throwable) -> {
            if (throwable != null) {
                Log.d(TAG, "????????? ?????? ?????? ??????" + throwable.toString());
                //????????? ???????????? ?????? ??????????????? KaKao?????? ????????????. KOE + ??????
            } else {
                //[ { } ] json ???????????? ?????? ???????????? ????????? ????????? Account?????? ?????? ????????? ?????????(????????????)
                //??? ????????? profile??? ?????? ??? ?????? ??????.
                Account account = user.getKakaoAccount();
                if (account != null) {
                    Log.d(TAG, "????????? ???????????? ??????" + account.getProfile());
                    Log.d(TAG, "????????? ???????????? ??????" + account.getEmail());
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