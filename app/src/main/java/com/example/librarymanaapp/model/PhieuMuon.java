package com.example.librarymanaapp.model;

import java.util.Date;

public class PhieuMuon {
    public int maPM;
    public String maTT;
    public int maTV;
    public int maSach;
    public String ngay;
    public int tienThue;
    public int traSach;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, String ngay, int tienThue, int traSach) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.tienThue = tienThue;
        this.traSach = traSach;
    }

    @Override
    public String toString() {
        return "PhieuMuon{" +
                "maPM=" + maPM +
                ", maTT='" + maTT + '\'' +
                ", maTV=" + maTV +
                ", maSach=" + maSach +
                ", ngay='" + ngay + '\'' +
                ", tienThue=" + tienThue +
                ", traSach=" + traSach +
                '}';
    }
}
