package com.example.librarymanaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.dao.ThuThuDAO;
import com.example.librarymanaapp.model.ThuThu;


public class frgAddUser extends Fragment {
    EditText edUser,edHoTen,edPass,edRePass;
    Button btnSave,btnCancel;
    ThuThuDAO dao;

    public frgAddUser() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        edUser = view.findViewById(R.id.edUser);
        edHoTen = view.findViewById(R.id.edHoTen);
        edPass = view.findViewById(R.id.edPass);
        edRePass = view.findViewById(R.id.edRePass);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);

        dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(view1 -> {
            edUser.setText("");
            edHoTen.setText("");
            edPass.setText("");
            edRePass.setText("");
        });

        btnSave.setOnClickListener(view1 -> {
            ThuThu thuThu = new ThuThu();
            thuThu.maTT = edUser.getText().toString();
            thuThu.hoTen = edHoTen.getText().toString();
            thuThu.matKhau = edPass.getText().toString();
            if(validate() >0){
                Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                edUser.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edRePass.setText("");
            }else{
                Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public int validate(){
        int check =1;
        if(edUser.getText().length()==0 || edHoTen.getText().length()==0 || edPass.getText().length()==0 || edRePass.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}