//package com.example.librarymanaapp.dao;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.librarymanaapp.database.DbHelper;
//import com.example.librarymanaapp.model.Sach;
//import com.example.librarymanaapp.model.Top;
//
//import java.lang.reflect.Constructor;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ThongKeDAO {
//
//    private SQLiteDatabase db;
//    private Context context;
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    public ThongKeDAO(Context context){
//        this.context = context;
//        DbHelper dbHelper = new DbHelper(context);
//        db = dbHelper.getWritableDatabase();
//    }
//    //thống kê top 10
//    @SuppressLint("Range")
//    public List<Top> getTop(){
//        String sqlTop = "SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
//        List<Top> list = new ArrayList<Top>();
//        SachDAO sachDao = new SachDAO(context);
//        Cursor c = db.rawQuery(sqlTop,null);
//        while (c.moveToNext()){
//            Top top = new Top();
//            @SuppressLint("Range") Sach sach = sachDao.getID(c.getString(c.getColumnIndex("maSach")));
//            top.tenSach = sach.tenSach;
//            top.soLuong = Integer.parseInt((c.getString(c.getColumnIndex("soLuong"))));
//            list.add(top);
//        }
//        return list;
//    }
//
//    // thống kê doanh thu
//
//}
