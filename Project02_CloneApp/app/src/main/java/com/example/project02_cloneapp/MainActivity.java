package com.example.project02_cloneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project02_cloneapp.chat.ChatFragment;
import com.example.project02_cloneapp.friend.FriendFragment;
import com.example.project02_cloneapp.shop.ShopFragment;
import com.example.project02_cloneapp.view.ViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar; //전역변수로 ActionBar를 선언만 해둔다 .//현 ) null
    BottomNavigationView bottom_nav;
    final String TAG = "메인액티비티";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //actionbar는 안드로이드 os가 제공을 해줌. 따라서 os를 이용해서 값을 할당
        actionBar = getSupportActionBar(); //os가 현재  ActionBar를 리턴
        actionBar.setTitle("친구");
        changeFragment(new FriendFragment());
       // actionBar.hide(); <= 전체 화면으로 컨텐트 부분을 더 넓게 보여주고 싶다면
        //해당하는 페이지에서 숨김 처리도 가능함.
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //바텀 네비게이션 메뉴가 바귈때마다 토스트창 띄우기
                //프래그먼트가 각각의 화면에 맞게 전환됨.
                if (item.getItemId() == R.id.tab1) {
                    Log.d(TAG, "네비게이션: 친구");
                    actionBar.setTitle("친구");
                    changeFragment(new FriendFragment());
                    //메소드, 정의부(변수입력(파라메터 입력부분
                }else if (item.getItemId() == R.id.tab2) {
                    Log.d(TAG, "네비게이션: 채팅");
                    actionBar.setTitle("채팅");
                    changeFragment(new ChatFragment());
                }else if (item.getItemId() == R.id.tab3) {
                    changeFragment(new ViewFragment());
                    Log.d(TAG, "네비게이션: 뷰");
                }else if (item.getItemId() == R.id.tab4){
                    Log.d(TAG, "네비게이션: 쇼핑");
                    actionBar.setTitle("쇼핑");
                    changeFragment(new ShopFragment());
                }else if(item.getItemId()==R.id.tab5){
                    Log.d(TAG, "네비게이션: 기타");
                }else{
                    return false;
                }
              return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴 파일을 만들어서 R.menu. 내가 만든 xml 파일 지정해주면 붙음
        getMenuInflater().inflate(R.menu.option_menu,menu);  //외우지 말기(LayoutInflater <= 레이아웃붙이기 이건 외우기)

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //어떤 아이템 선택 되었는지 또 구분할 수 있음

     if(item.getItemId()==R.id.menu_music){
            Log.d("clicked","음악 클릭");
            Toast.makeText(this, "음악 클릭", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //프래그먼트 전환을 위한 메소드
    //메소드를 활용여 각 다른 프래그먼트를 보여주는 처리
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}