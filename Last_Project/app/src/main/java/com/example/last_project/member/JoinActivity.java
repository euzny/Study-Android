package com.example.last_project.member;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.last_project.R;
import com.example.last_project.common.AskTask;
import com.example.last_project.common.CommonMethod;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinActivity extends AppCompatActivity {
    ImageView imgv_profile;
    EditText edt_id, edt_pw, edt_name;
    Button btn_join, btn_cancel;
    File imgFile;       //<= 이미지를 담을 수 있는 그릇(임시로 파일을 생성하고 프로바이더가 내용을 해당하는 파일에 담음)
    String imgFilePath=""; // <= 이미지를 파일로 바꾸게끔 해주는 경로 저장용
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        checkDangerousPermissions();
        imgv_profile= findViewById(R.id.imgv_profile);
        edt_id = findViewById(R.id.edt_id);
        edt_pw=findViewById(R.id.edt_pw);
        edt_name=findViewById(R.id.edt_name);
        btn_join= findViewById(R.id.btn_join);
        btn_cancel=findViewById(R.id.btn_cancel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        imgv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카메라 또는 갤러리로 사진을 넣을수있게끔 유도한다.(사용자)
                showDialog();
                Log.d("태그", "onClick: 33");
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask task = new AskTask("file.f");
                task.addParam("id",edt_id.getText().toString());
                task.addParam("pw",edt_pw.getText().toString());
                task.addParam("name",edt_name.getText().toString());
                task.addFileParam("file",imgFilePath);  //실제 이미지 경로를 보냄 Ask new FileBody new File =>
                CommonMethod.executeAskGet(task);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    String[] dialogItem = { "카메라" , "갤러리"};
    final int CAMERA_IMAGE = 1002;
    final int LOAD_IMAGE = 1001;
    //사진을 사용자가 카메라로 찍을껀지 또는 갤러리로 선택할건지를 정하는 메소드.
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선택해주세요.")
                .setSingleChoiceItems(dialogItem, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int idx) {
                        Log.d("태그", " idx : " + idx);
                        if(dialogItem[idx].equals("카메라")){
                            Log.d("태그", "onClick: 카메라가 선택 됨");
                            go_Camera();
                        }else{
                            Log.d("태그", "onClick: 갤러리가 선택 됨");
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            //startActivity <= 액티비티를 시작함
                            //startActivityForResult < = 어떤 액티비티를 시작하고 결과를 받아온다.
                            // 어떤 액티비티를 시작했는지 알 수 있는 구분자가 필요함
                            startActivityForResult(Intent.createChooser(intent,"사진을 고르세요"),LOAD_IMAGE);
                        }
                    }
                });
        AlertDialog dialog = builder.create();//초기화 String aa = "";
        dialog.show();
    }

    //forResult로 실행을 하고 그 액티비티가 종료되면 반드시 실행이 됨
    // 카메라 => 액티비티로 뜸 => 사진찍고 끔 => 1000
    //갤러리 => 액티비티 뜸 => 사진고름 끔 =>    1001
    //네이버 지도 액티비티 띄움 => 위치고름 => 끔 =>    1002
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //fakedirectory
        if(requestCode == LOAD_IMAGE && resultCode == RESULT_OK){
            Uri  selectetImgUri = data.getData();
            imgFilePath = getRealPath(selectetImgUri);
            Log.d("커서", getRealPath(selectetImgUri)+"");
            Log.d("커서", selectetImgUri.toString());
            Glide.with(JoinActivity.this).load(selectetImgUri).into(imgv_profile);
        }else if(requestCode==CAMERA_IMAGE && resultCode==RESULT_OK){
            //CreateFile을 했을때 이미지의 실제경로가 이미 생성이 되어있음
            Glide.with(JoinActivity.this).load(imgFilePath).into(imgv_profile);
        }
    }

    public void go_Camera(){
            Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //해당하는 기능 (인텐트가 사용가능한 상태인지를 체크함)
            if(picIntent.resolveActivity(getPackageManager()) != null){
                    imgFile =null;
                    //임시 파일을 만드는 로직
                    imgFile = createFile();
                    if(imgFile != null){
                                //API 24 이상부터는 FiileProvider를 제공해야함
                            Uri imgUri = FileProvider.getUriForFile(getApplicationContext(),
                                    getApplicationContext().getPackageName()+".fileprovider", imgFile);

                            //만약에 API24 이상이면(체크)
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                                        picIntent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
                            }else{
                                picIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imgFile));
                            }
                            startActivityForResult(picIntent,CAMERA_IMAGE);
                    }
            }
    }

    private File createFile(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //UUID <= 파일이름이 언제 생성했는지 알아보기가 힘듬 => 나중에 안정화되고 바꿔도됨.
        String imageFileName ="And"+timeStamp;
        //사진파일을 저장하기 위한 경로
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File curFile = null;
        //임시파일을 생성함
        try{
            curFile=File.createTempFile(imageFileName,".jpg",storageDir);
        }catch (IOException e){
            e.printStackTrace();
        }
       imgFilePath = curFile.getAbsolutePath(); //절대경로를 저장함 . 나중에
        return curFile;
    }


    //여기 부분은 따로 공부 x , 사용만 하고 현장에 가면 다른 메소드가 이미 있음
    public String getRealPath( Uri  selectetImgUri ){
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectetImgUri,proj,null,null,null);
        if(cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);

        }
        return res;
    }
}