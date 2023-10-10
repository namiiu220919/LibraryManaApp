package com.example.librarymanaapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.librarymanaapp.database.DbHelper;
import com.example.librarymanaapp.model.Sach;
import com.example.librarymanaapp.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert (Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaThue());
        values.put("maLoai",obj.getMaLoai());

        return db.insert("Sach", null, values);
    }

    public int update(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaThue());
        values.put("maLoai",obj.getMaLoai());

        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(obj.getMaSach())});
    }

    public int delete(String id){
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    //get All data
    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    //get data theo id
    public Sach getID(String id){
        String sql ="SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<Sach>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            obj.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(obj);
            Log.i("//======",obj.toString());
        }
        return list;
    }
}
