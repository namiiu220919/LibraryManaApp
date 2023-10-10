package com.example.librarymanaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.model.LoaiSach;
import com.example.librarymanaapp.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTv, tvTenTV;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final ThanhVien item = list.get(position);
        if(item != null){
            tvMaTv = v.findViewById(R.id.tvMaTVSp);
            tvMaTv.setText(item.getMaTV() + ". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final ThanhVien item = list.get(position);
        if(item != null){
            tvMaTv = v.findViewById(R.id.tvMaTVSp);
            tvMaTv.setText(item.getMaTV() + ". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }
}
