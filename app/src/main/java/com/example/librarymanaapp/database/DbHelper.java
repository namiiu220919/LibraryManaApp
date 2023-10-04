package com.example.librarymanaapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="PNLIB";
    static final int dbVersion=1;
    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng ThuThu
        String createTableThuThu="create table ThuThu (" +
                "maTT TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

        String dataThuThu = "insert into ThuThu values" +
                "('admin','Adminitrator','admin')," +
                "('TT01','Nguyễn Văn Đông','123456')," +
                "('TT02','Nguyễn Thị Hường','123456')," +
                "('TT03','Nguyễn Đông Nhi','123456')";
        db.execSQL(dataThuThu);

        //Tạo bảng ThanhVien
        String createTableThanhVien="create table ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        String dataThanhVien = "insert into ThanhVien(hoTen, namSinh) values" +
                "('Nguyễn Phương Nam','2004')," +
                "('Lưu Tuấn Quỳnh','2005')," +
                "('Đào Văn Mạnh','2003')," +
                "('Hoàng Quang Vinh','2001')";
        db.execSQL(dataThanhVien);

        //Tạo bảng LoaiSach
        String createTableLoaiSach="create table LoaiSach (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        String dataLoaiSach = "insert into LoaiSach(tenLoai) values" +
                "('IT')," +
                "('Toán')," +
                "('Marketing')," +
                "('Logistic')";
        db.execSQL(dataLoaiSach);

        //Tạo bảng Sach
        String createTableSach="create table Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL," +
                "giaThue INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        String dataSach = "insert into Sach(tenSach,giaThue,maLoai) values" +
                "('JS Cơ bản',10000,1)," +
                "('Toán Nâng Cao',12000,2)," +
                "('Tâm lý khách hàng',10000,3)," +
                "('Vận chuyển đường biển',11000,4)";
        db.execSQL(dataSach);

        //Tạo bảng PhieuMuon
        String createTablePhieuMuon ="create table PhieuMuon (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT REFERENCES ThuThu(maTT)," +
                "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                "maSach INTEGER REFERENCES Sach(maSach)," +
                "tienThue INTEGER NOT NULL," +
                "ngay TEXT NOT NULL," +
                "traSach INTEGER NOT NULL)";
        db.execSQL(createTablePhieuMuon);

        String dataPhieuMuon = "insert into PhieuMuon(maTT,maTV,maSach,tienThue,ngay,traSach)values" +
                "('TT01',1,1,10000,'20/9/2023',1)," +
                "('TT01',2,2,12000,'20/9/2023',2)," +
                "('TT02',3,3,10000,'20/9/2023',1)," +
                "('TT03',4,4,11000,'20/9/2023',2)";
        db.execSQL(dataPhieuMuon);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
