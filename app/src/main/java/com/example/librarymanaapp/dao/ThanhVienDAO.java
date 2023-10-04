package com.example.librarymanaapp.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.librarymanaapp.database.DbHelper;
import com.example.librarymanaapp.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert (ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.hoTen);
        values.put("namSinh",obj.namSinh);

        return db.insert("ThanhVien", null, values);
    }

    public int update(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.hoTen);
        values.put("namSinh",obj.namSinh);

        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(obj.maTV)});
    }

    public int delete(String id){
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    //get All data
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    //get data theo id
    public ThanhVien getID(String id){
        String sql ="SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    private List<ThanhVien> getData(String sql, String...selectionArgs){
        ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.maTV=Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.hoTen=c.getString(c.getColumnIndex("hoTen"));
            obj.namSinh=c.getString(c.getColumnIndex("namSinh"));
            Log.i("//======",obj.toString());
            list.add(obj);
        }
        return list;
    }
}
