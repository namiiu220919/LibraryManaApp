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
import com.example.librarymanaapp.dao.LoaiSachDAO;
import com.example.librarymanaapp.fragment.frgSach;
import com.example.librarymanaapp.fragment.frgThanhVien;
import com.example.librarymanaapp.model.LoaiSach;
import com.example.librarymanaapp.model.Sach;
import com.example.librarymanaapp.model.ThanhVien;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    frgSach frg;
    List<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView btnDelete;
    public SachAdapter(@NonNull Context context, frgSach frg, List<Sach> list) {
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
            v = inflater.inflate(R.layout.item_sach,null);
        }

        final Sach item = list.get(position);
        if(item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSach=v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã sách: "+item.getMaSach());
            tvTenSach=v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+item.getTenSach());
            tvGiaThue=v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá thuê: "+item.getGiaThue());
            tvLoai=v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại sách: "+loaiSach.getTenLoai());
            btnDelete =v.findViewById(R.id.btnDeleteS);
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //xoa
            }
        });

        return v;
    }
}
