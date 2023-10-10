package com.example.librarymanaapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.dao.SachDAO;
import com.example.librarymanaapp.dao.ThanhVienDAO;
import com.example.librarymanaapp.fragment.frgPhieuMuon;
import com.example.librarymanaapp.model.PhieuMuon;
import com.example.librarymanaapp.model.Sach;
import com.example.librarymanaapp.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    frgPhieuMuon frg;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView btnDelete;
    ThanhVienDAO thanhVienDAO;
    SachDAO sachDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonAdapter(@NonNull Context context, frgPhieuMuon frg, ArrayList<PhieuMuon> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.frg = frg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_phieumuon, null);
        }
        final PhieuMuon item = list.get(position);
        if (item != null) {
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu: " + item.getMaPM());
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: " + sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên: " + thanhVien.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: " + item.getTienThue());
            tvNgay = v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: " + sdf.format(item.getNgay()));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.getTraSach() == 1) {
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            } else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            btnDelete = v.findViewById(R.id.btnDeletePM);
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frg.xoa(String.valueOf(item.getMaPM()));
            }
        });
        return v;
    }

}
