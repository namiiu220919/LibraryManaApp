package com.example.librarymanaapp.model;

public class ThanhVien {
    public int maTV;
    public String hoTen;
    public String namSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, String namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maTV=" + maTV +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh='" + namSinh + '\'' +
                '}';
    }
}
