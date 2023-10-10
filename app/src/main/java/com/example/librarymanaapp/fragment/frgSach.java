package com.example.librarymanaapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.adapter.LoaiSachSpinnerAdapter;
import com.example.librarymanaapp.adapter.SachAdapter;
import com.example.librarymanaapp.dao.LoaiSachDAO;
import com.example.librarymanaapp.dao.SachDAO;
import com.example.librarymanaapp.model.LoaiSach;
import com.example.librarymanaapp.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class frgSach extends Fragment {
    ListView lstSach;
    ArrayList<Sach> list;
    FloatingActionButton fltAdd;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnSave,btnCancel;
    static SachDAO dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLS;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoai, position;

    public frgSach() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v = inflater.inflate(R.layout.fragment_sach, container, false);
       lstSach = v.findViewById(R.id.lstSach);
       fltAdd = v.findViewById(R.id.fltAdd);
       dao = new SachDAO(getActivity());
       capnhatLst();
       fltAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openDialog(getActivity(),0);
           }
       });
       lstSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        listLS = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLS = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context,listLS);
        spinner.setAdapter(spinnerAdapter);

        spinner.setAdapter(spinnerAdapter);
        //lấy mã loại sách
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoai = listLS.get(i).getMaLoai();
                Toast.makeText(context, "Chọn " + listLS.get(i).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //kiểm tra type insert 0 hay Update 1
        edMaSach.setEnabled(false);
        if (type !=0){
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < listLS.size(); i++)
                if(item.getMaLoai() == (listLS.get(i).getMaLoai())){
                    position = i;
                }
            Log.i("demo","posSach" + position);
            spinner.setSelection(position);
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
                try {
                    item = new Sach();
                    item.setTenSach(edTenSach.getText().toString());
                    item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                    item.setMaLoai(maLoai);
                    if(validate()>0) {
                        if (type == 0) {
                            //type = 0 (insert)
                            if (dao.insert(item) > 0) {
                                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //type == 1 (update)
                            item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                            if (dao.update(item) > 0) {
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        capnhatLst();
                        dialog.dismiss();
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
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
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        lstSach.setAdapter(adapter);
    }
    public int validate(){
        int check =1;
        if(edTenSach.getText().length() ==0 || edGiaThue.getText().length()==0){
            Toast.makeText(getContext(), "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}