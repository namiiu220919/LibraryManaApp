package com.example.librarymanaapp.model;

public class ThuThu {
    public String maTT;
    public String hoTen;
    public String matKhau;

    public ThuThu() {
    }

    public ThuThu(String maTT, String hoTen, String matKhau) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "ThuThu{" +
                "maTT='" + maTT + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", matKhau='" + matKhau + '\'' +
                '}';
    }
}
