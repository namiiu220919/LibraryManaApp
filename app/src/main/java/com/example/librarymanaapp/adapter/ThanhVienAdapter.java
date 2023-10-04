package com.example.librarymanaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.fragment.frgThanhVien;
import com.example.librarymanaapp.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    frgThanhVien frg;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV,tvHoTen,tvNamSinh;
    ImageButton btnUpdate, btnDelete;

    public ThanhVienAdapter(@NonNull Context context,ArrayList<ThanhVien> list) {
        super(context,0,list);
        this.context =context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_thanhvien,null);
        }
        final ThanhVien item = list.get(position);
        if(item!=null){
            tvMaTV=v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên: " + item.maTV);
            tvHoTen=v.findViewById(R.id.tvHoTen);
            tvHoTen.setText("Họ tên: " + item.hoTen);
            tvNamSinh=v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + item.namSinh);
            btnUpdate=v.findViewById(R.id.btnUpdate);
            btnDelete=v.findViewById(R.id.btnDelete);
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frg.xoa(String.valueOf(item.maTV));
            }
        });
        return v;
    }
}
