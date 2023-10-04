package com.example.librarymanaapp.fragment;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.android.material.textfield.TextInputEditText;


public class frgChangePass extends Fragment {

    EditText edPassOld, edNewPass, edRePass;
    Button btnSave, btnCancle;
    ThuThuDAO dao;

    public frgChangePass() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass,container,false);
        edPassOld= view.findViewById(R.id.edOldPass);
        edNewPass= view.findViewById(R.id.edNewPass);
        edRePass= view.findViewById(R.id.edRePass);
        btnSave= view.findViewById(R.id.btnSave);
        btnCancle= view.findViewById(R.id.btnCancel);

        dao = new ThuThuDAO(getActivity());

        btnCancle.setOnClickListener(view1 -> {
            edPassOld.setText("");
            edNewPass.setText("");
            edRePass.setText("");
        });
        btnSave.setOnClickListener(view1 -> {
            //đọc user, pass trong SharePreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String user = pref.getString("USERNAME","");
            if(validate() >0){
                ThuThu thuThu = dao.getID(user);
                thuThu.matKhau = edNewPass.getText().toString();

                if (dao.updatePass(thuThu) > 0){
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    edPassOld.setText("");
                    edNewPass.setText("");
                    edRePass.setText("");
                }else{
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }
    public int validate(){
        int check =1;
        if(edPassOld.getText().length()==0 || edNewPass.getText().length()==0 || edRePass.getText().length()==0){
            Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }else{
            //đọc user, pass trong SharePreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String newPass = edNewPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!newPass.equals(rePass)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}