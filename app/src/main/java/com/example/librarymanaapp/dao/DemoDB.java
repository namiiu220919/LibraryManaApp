package com.example.librarymanaapp.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.librarymanaapp.database.DbHelper;
import com.example.librarymanaapp.model.ThanhVien;
import com.example.librarymanaapp.model.ThuThu;

public class DemoDB {
    private SQLiteDatabase db;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    PhieuMuonDAO phieuMuonDAO;
    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;
    static final String TAG ="//======";
    public DemoDB(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
        phieuMuonDAO = new PhieuMuonDAO(context);
        loaiSachDAO = new LoaiSachDAO(context);
        sachDAO = new SachDAO(context);
    }
    public void thanhVien(){
//        ThanhVien tv = new ThanhVien(1,"Phương Nam","2004");
//        if(thanhVienDAO.insert(tv) > 0){
//            Log.i(TAG,"Thêm thành viên thành công");
//        }else{
//            Log.i(TAG,"Thêm thành viên thất bại");
//        }
        Log.i(TAG,"Tổng sô thành viên:" +thanhVienDAO.getAll().size());

    }

    public void thuThu(){

        Log.i(TAG,"Tổng sô thuthu:" +thuThuDAO.getAll().size());
    }
    public void phieuMuon(){
        Log.i(TAG,"Tổng sô phiếu mượn:" +phieuMuonDAO.getAll().size());
    }
    public void sach(){
        Log.i(TAG,"Tổng sô sách:" +sachDAO.getAll().size());
    }
    public void loaiSach(){
        Log.i(TAG,"Tổng sô loại sách:" +loaiSachDAO.getAll().size());
    }
}
