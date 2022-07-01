package com.example.last_project.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import  com.example.last_project.R;
import com.example.last_project.common.AskTask;
import com.example.last_project.common.CommonMethod;
import com.google.gson.Gson;

/*AppCompat <= 전체화면으로 켜져야함 */
public class CusDetailActivity extends Activity {
    TextView tv_info, tv_name, tv_id,  tv_phone;
    RadioButton tv_female,tv_male;
    Button btn_delete, btn_modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cus_detail);

        Intent intent = getIntent();
        CustomerDTO dto = (CustomerDTO) intent.getSerializableExtra("dto");
        Log.d("####", "onCreate: "+dto.getName());

        tv_info = findViewById(R.id.tv_info);
        tv_name= findViewById(R.id.tv_name);
        tv_female =findViewById(R.id.tv_female);
        tv_phone = findViewById(R.id.tv_phone);
        tv_male = findViewById(R.id.tv_male);
        tv_id = findViewById(R.id.tv_id);

        btn_delete=findViewById(R.id.btn_delete);
        btn_modify =findViewById(R.id.btn_modify);


        tv_id.setText(dto.getId()+"");
        tv_info.setText(dto.getName());
        tv_phone.setText(dto.getPhone());
        tv_name.setText(dto.getName());


        
        if(dto.getGender().equals("남")){
            tv_male.setChecked(true);
        }else{
            tv_female.setChecked(true);
        }
        tv_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        tv_female.setChecked(false);
                    }
            }
        });
        tv_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tv_male.setChecked(false);
                }
            }
        });

        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_name.getText().toString().length()<1){
                    Toast.makeText(CusDetailActivity.this,"이름은 반드시 입력해야합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                dto.setName(tv_name.getText()+"");
                dto.setGender(tv_male.isChecked() == true ? "남"  : "여");
                dto.setPhone(tv_phone.getText()+"");
                AskTask task = new AskTask("update.cu");
                task.addParam("dto",gson.toJson(dto));
                CommonMethod.executeAskGet(task); //true false / int ..etc
                finish(); //테스트가 올바르게 끝나고 나서
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                AskTask task = new AskTask("delete.cu");
                task.addParam("dto",gson.toJson(dto));
                CommonMethod.executeAskGet(task); //true false / int ..etc
                finish(); //테스트가 올바르게 끝나고 나서
            }
        });
    }
}