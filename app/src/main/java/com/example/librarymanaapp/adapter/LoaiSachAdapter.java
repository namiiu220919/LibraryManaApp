package com.example.librarymanaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.fragment.frgLoaiSach;
import com.example.librarymanaapp.model.LoaiSach;


import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    frgLoaiSach frg;
    ArrayList<LoaiSach> list;
    TextView tvMaLoai,tvTenLoai;
    ImageView btnDelete;
    public LoaiSachAdapter(@NonNull Context context, frgLoaiSach frg, ArrayList<LoaiSach> list) {
        super(context,0,list);
        this.context =context;
        this.frg = frg;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaisach,null);
        }
        final LoaiSach item = list.get(position);
        if(item != null){
            tvMaLoai = v.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Mã loại: " + item.getMaLoai());
            tvTenLoai = v.findViewById(R.id.tvTenSach);
            tvTenLoai.setText("Tên loại: " + item.getTenLoai());
            btnDelete = v.findViewById(R.id.btnDeleteLS);
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frg.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }

}
