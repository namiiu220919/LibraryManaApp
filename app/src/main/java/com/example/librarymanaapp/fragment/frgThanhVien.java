package com.example.librarymanaapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.adapter.ThanhVienAdapter;
import com.example.librarymanaapp.dao.ThanhVienDAO;
import com.example.librarymanaapp.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frgThanhVien extends Fragment {
    ListView lstTV;
    ArrayList<ThanhVien> list;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    FloatingActionButton fltAdd;
    Dialog dialog;
    EditText edMaTV,edHoTen,edNamSinh;
    Button btnSave, btnCancel;
    public frgThanhVien() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lstTV = v.findViewById(R.id.lstThanhVien);
        dao = new ThanhVienDAO(getActivity());
        fltAdd = v.findViewById(R.id.fltAdd);
        capnhatLst();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });

        lstTV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item=list.get(i);
                openDialog(getActivity(),1);
                return false;
            }
        });

        return v;
    }
    void capnhatLst(){
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        lstTV.setAdapter(adapter);
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
    protected void openDialog(final Context context, final int type){
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanhvien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edHoTen = dialog.findViewById(R.id.edHoTen);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        //kiểm tra type insert 0 hay Update 1
        edMaTV.setEnabled(false);
        if(type !=0){
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edHoTen.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
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
                item = new ThanhVien();
                item.setHoTen(edHoTen.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
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
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
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
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(edHoTen.getText().length() ==0 || edNamSinh.getText().length()==0){
            Toast.makeText(getContext(), "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}