package com.example.last_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.last_project.common.CommonVal;
import com.example.last_project.customer.CusFragment;
import com.example.last_project.employee.EmpFragment;
import com.example.last_project.map.MapActivity;
import com.example.last_project.member.JoinActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "메인";
    BottomNavigationView btm_nav;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView nav_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DrawerLayout <= 카톡 을 키고 아무 대화방이나 들어가서 ,Toggle Button (우측상단)
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        toolbar = findViewById(R.id.toolbar);
        btm_nav = findViewById(R.id.btm_nav);
        drawer = findViewById(R.id.drawer);
        nav_view =findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this , drawer , toolbar , R.string.navigation_drawer_open ,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = nav_view.getHeaderView(0);
        TextView tv_id = headerView.findViewById(R.id.tv_id);
        tv_id.setText(CommonVal.loginInfo.getId());
        TextView tv_desc = headerView.findViewById(R.id.tv_desc);
        tv_desc.setText("상세 설명~~");

        ImageView imgv = headerView.findViewById(R.id.imgv);
        if(CommonVal.loginInfo.getImagepath()!= null){
            Glide.with(MainActivity.this).load(CommonVal.loginInfo.getImagepath()).into(imgv);
        }


        changeFragment(new CusFragment());
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_cus){
                    changeFragment(new CusFragment());
                    Log.d(TAG, "cus");
                }else if(item.getItemId() == R.id.menu_emp){
                    changeFragment(new EmpFragment());
                    Log.d(TAG, "emp");
                }else if(item.getItemId()==R.id.menu_noti){
                    changeFragment(new NotiFragment());
                    Log.d(TAG, "noti");
                }else if(item.getItemId()==R.id.menu_map){
                   Intent intent = new Intent(MainActivity.this, MapActivity.class);
                   startActivity(intent);
                }
                return true;
            }
        });

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                
                if(item.getItemId() == R.id.nav_home){
                    Log.d(TAG, "onNavigationItemSelected: ");
                    drawer.closeDrawer(GravityCompat.START);
                }
                
                return true;
            }
        });
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}