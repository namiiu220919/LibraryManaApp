package com.example.librarymanaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.librarymanaapp.dao.DemoDB;
import com.example.librarymanaapp.dao.ThuThuDAO;
import com.example.librarymanaapp.fragment.frgAddUser;
import com.example.librarymanaapp.fragment.frgChangePass;
import com.example.librarymanaapp.fragment.frgDoanhThu;
import com.example.librarymanaapp.fragment.frgLoaiSach;
import com.example.librarymanaapp.fragment.frgPhieuMuon;
import com.example.librarymanaapp.fragment.frgSach;
import com.example.librarymanaapp.fragment.frgThanhVien;
import com.example.librarymanaapp.model.PhieuMuon;
import com.example.librarymanaapp.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    ThuThuDAO thuThuDAO;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tạo database
//        DemoDB demoDB = new DemoDB(getApplicationContext());
//        demoDB.thuThu();
//        demoDB.thanhVien();
//        demoDB.loaiSach();
//        demoDB.sach();
//        demoDB.phieuMuon();
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        //set toolbar thay thế cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        //dùng fragment_PhieuMuon làm home
        FragmentManager manager = getSupportFragmentManager();
        frgPhieuMuon frgPM = new frgPhieuMuon();
        manager.beginTransaction()
                .replace(R.id.flContent,frgPM)
                .commit();

        NavigationView nv = findViewById(R.id.nvView);
        //show user in header
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i .getStringExtra("user");
//        thuThuDAO = new ThuThuDAO(this);
//        ThuThu thuThu = thuThuDAO.getID(user);
//        String username = thuThu.hoTen;
//        edUser.setText("Welcome" + username);

//        admin có quyền add user
//        if(user.equalsIgnoreCase("admin")){
//            nv.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
//        }
        //Điều hướng navigation
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager1 = getSupportFragmentManager();
                if(item.getItemId()==R.id.nav_PhieuMuon){
                    setTitle("Quản lý Phiếu mượn");
                        frgPhieuMuon frgPhieuMuon = new frgPhieuMuon();
                        manager1.beginTransaction()
                                .replace(R.id.flContent,frgPhieuMuon)
                                .commit();
                }
                if(item.getItemId()==R.id.nav_LoaiSach){
                    setTitle("Quản lý Loại Sách");
                        frgLoaiSach frgLoaiSach = new frgLoaiSach();
                        manager1.beginTransaction()
                                .replace(R.id.flContent,frgLoaiSach)
                                .commit();
                }
                if(item.getItemId()==R.id.nav_Sach){
                    setTitle("Quản lý Sách");
                        frgSach frgSach = new frgSach();
                        manager1.beginTransaction()
                                .replace(R.id.flContent,frgSach)
                                .commit();
                }
                if(item.getItemId()==R.id.nav_ThanhVien){
                    setTitle("Quản lý phiếu mượn");
                        frgThanhVien  frgThanhVien = new frgThanhVien();
                        manager1.beginTransaction()
                                .replace(R.id.flContent, frgThanhVien)
                                .commit();
                }
                if(item.getItemId()==R.id.sub_DoanhThu){
                    setTitle("Thống kê doanh thu");
                        frgDoanhThu frgDoanhThu = new frgDoanhThu();
                        manager1.beginTransaction()
                                .replace(R.id.flContent, frgDoanhThu)
                                .commit();
                }
                if(item.getItemId()==R.id.sub_AddUser){
                    setTitle("Thêm người dùng");
                        frgAddUser frgAddUser = new frgAddUser();
                        manager1.beginTransaction()
                                .replace(R.id.flContent, frgAddUser)
                                .commit();
                }
                if(item.getItemId()==R.id.sub_Pass){
                    setTitle("Thay đổi mật khẩu");
                        frgChangePass frgChangePass = new frgChangePass();
                        manager1.beginTransaction()
                                .replace(R.id.flContent, frgChangePass)
                                .commit();
                }
                if(item.getItemId()==R.id.sub_Logout){
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                }
            drawer.closeDrawers();
            return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}