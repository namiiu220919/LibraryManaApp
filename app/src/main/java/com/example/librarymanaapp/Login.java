package com.example.librarymanaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.librarymanaapp.dao.ThuThuDAO;

public class Login extends AppCompatActivity {

    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    ThuThuDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);

    dao = new ThuThuDAO(this);

        //đọc user, pass trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUserName.setText("");
                edPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    public void checkLogin(){
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if(strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
        }else{
            if (dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                remembeUser(strUser,strPass,chkRememberPass.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void remembeUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            //xoá tình trạng đã lưu trước đó
            edit.clear();
        }else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //lưu lại toàn bộ
        edit.commit();
    }
}