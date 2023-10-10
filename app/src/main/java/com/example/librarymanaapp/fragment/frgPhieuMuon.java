package com.example.librarymanaapp.fragment;

import static java.time.MonthDay.now;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.adapter.PhieuMuonAdapter;
import com.example.librarymanaapp.adapter.SachSpinnerAdapter;
import com.example.librarymanaapp.adapter.ThanhVienSpinnerAdapter;
import com.example.librarymanaapp.dao.PhieuMuonDAO;
import com.example.librarymanaapp.dao.SachDAO;
import com.example.librarymanaapp.dao.ThanhVienDAO;
import com.example.librarymanaapp.model.PhieuMuon;
import com.example.librarymanaapp.model.Sach;
import com.example.librarymanaapp.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class frgPhieuMuon extends Fragment {
    ListView lstPM;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fltAdd;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDao;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public frgPhieuMuon() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lstPM= v.findViewById(R.id.lstPhieuMuon);
        fltAdd = v.findViewById(R.id.fltAddPM);
        dao = new PhieuMuonDAO(getActivity());
        capnhatLst();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        lstPM.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    protected void openDialog(final Context context, final int type){
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.phieumuon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnSave = dialog.findViewById(R.id.btnSavePM);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);

        //lấy maThanhVien
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maThanhVien = listThanhVien.get(i).getMaTV();
                Toast.makeText(context, "Chọn " + listThanhVien.get(i).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sachDao = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDao.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        //lấy maLoaiSach
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = listSach.get(i).getMaSach();
                tienThue = listSach.get(i).getGiaThue();
                Toast.makeText(context, "Chọn"+ listSach.get(i).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edMaPM.setEnabled(false);
        if(type !=0){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for(int i = 0; i< listThanhVien.size();i++)
                if(item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            spTV.setSelection(positionTV);
            for(int i = 0; i< listSach.size();i++)
                if(item.getMaSach() == (listSach.get(i).getMaSach())){
                    positionSach = i;
                }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày thuê: " + sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: " + item.getTienThue() );
            if(item.getTraSach() == 1){
                chkTraSach.setChecked(true);
            }else{
                chkTraSach.setChecked(false);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if(chkTraSach.isChecked()){
                    item.setTraSach(1);
                }else{
                    item.setTraSach(0);
                }

                    if(type == 0){
                        //type = 0 (insert)
                        if(dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        //type == 1 (update)
                        item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                        if(dao.update(item)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capnhatLst();
                    dialog.dismiss();

            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        //sử dụng alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Chắc chắn xoá?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                dao.delete(Id);
                capnhatLst();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capnhatLst(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        lstPM.setAdapter(adapter);
    }

}