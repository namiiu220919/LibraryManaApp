package com.example.librarymanaapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.adapter.LoaiSachAdapter;
import com.example.librarymanaapp.dao.LoaiSachDAO;
import com.example.librarymanaapp.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class frgLoaiSach extends Fragment {
    ListView lstLoaiSach;
    ArrayList<LoaiSach> list;
    FloatingActionButton fltAdd;
    Dialog dialog;
    EditText edMaLoai, edTenLoai;
    Button btnSave, btnCancel;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    public frgLoaiSach() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lstLoaiSach = v.findViewById(R.id.lstLoaiSach);
        fltAdd=v.findViewById(R.id.fltAdd);
        dao = new LoaiSachDAO(getActivity());
        capnhatLst();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loaisach_dialog);
        edMaLoai = dialog.findViewById(R.id.edMaLoai);
        edTenLoai = dialog.findViewById(R.id.edTenLoai);
        btnSave = dialog.findViewById(R.id.btnSave);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        //kiểm tra type insert 0 hay update 1
        edMaLoai.setEnabled(false);
        if(type !=0 ){
            edMaLoai.setText(String.valueOf(item.getMaLoai()));
            edTenLoai.setText(item.getTenLoai());
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
                item = new LoaiSach();
                item.setTenLoai(edTenLoai.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        // type = 0 (insert)
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //type = 1 (update)
                        item.setMaLoai(Integer.parseInt(edMaLoai.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capnhatLst();
                    dialog.dismiss();
                }
            }
        });
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
        list =(ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        lstLoaiSach.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        if(edTenLoai.getText().length() == 0){
            Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}