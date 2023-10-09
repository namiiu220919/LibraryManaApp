package com.example.librarymanaapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.librarymanaapp.database.DbHelper;
import com.example.librarymanaapp.model.PhieuMuon;
import com.example.librarymanaapp.model.Sach;
import com.example.librarymanaapp.model.ThuThu;
import com.example.librarymanaapp.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public PhieuMuonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert (PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maPM",obj.getMaPM());
        values.put("maTT",obj.getMaTT());
        values.put("maTV",obj.getMaTV());
        values.put("maSach",obj.getMaSach());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tienThue",obj.getTienThue());
        values.put("traSach",obj.getTraSach());

        return db.insert("PhieuMuon", null, values);
    }

    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maPM",obj.getMaPM());
        values.put("maTT",obj.getMaTT());
        values.put("maTV",obj.getMaTV());
        values.put("maSach",obj.getMaSach());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tienThue",obj.getTienThue());
        values.put("traSach",obj.getTraSach());

        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.getMaPM())});
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
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            try{
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            }catch (ParseException e){
                e.printStackTrace();
            }
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            obj.setTraSach( Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(obj);
            Log.i("//======",obj.toString());
        }
        return list;
    }




}
