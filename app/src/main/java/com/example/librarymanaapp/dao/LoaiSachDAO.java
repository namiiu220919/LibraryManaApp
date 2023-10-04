package com.example.librarymanaapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.librarymanaapp.database.DbHelper;
import com.example.librarymanaapp.model.LoaiSach;
import com.example.librarymanaapp.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert (LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("maLoai",obj.maLoai);
        values.put("tenLoai",obj.tenLoai);

        return db.insert("LoaiSach", null, values);
    }

    public int update(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("maLoai",obj.maLoai);
        values.put("tenLoai",obj.tenLoai);

        return db.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(obj.maLoai)});
    }

    public int delete(String id){
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }

    //get All data
    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    //get data theo id
    public LoaiSach getID(String id){
        String sql ="SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql);
        return list.get(0);
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    private List<LoaiSach> getData(String sql, String...selectionArgs){
        ArrayList<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM LoaiSach",null);
        while (c.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.maLoai=Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            obj.tenLoai=c.getString(c.getColumnIndex("tenLoai"));
            Log.i("//======",obj.toString());
            list.add(obj);
        }

        return list;
    }
}
