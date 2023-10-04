package com.example.librarymanaapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.librarymanaapp.database.DbHelper;
import com.example.librarymanaapp.model.PhieuMuon;
import com.example.librarymanaapp.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    public PhieuMuonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert (PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maPM",obj.maPM);
        values.put("maTT",obj.maTT);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("ngay", obj.ngay);
        values.put("tienThue",obj.tienThue);
        values.put("traSach",obj.traSach);

        return db.insert("PhieuMuon", null, values);
    }

    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maPM",obj.maPM);
        values.put("maTT",obj.maTT);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("ngay", obj.ngay);
        values.put("tienThue",obj.tienThue);
        values.put("traSach",obj.traSach);

        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.maPM)});
    }

    public int delete(String id){
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    //get All data
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    //get data theo id
    public PhieuMuon getID(String id){
        String sql ="SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String...selectionArgs) {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.ngay = c.getString(c.getColumnIndex("ngay"));
            obj.tienThue = Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
            obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            list.add(obj);
            Log.i("//======",obj.toString());
        }
        return list;
    }
}
