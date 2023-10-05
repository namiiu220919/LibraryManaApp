package com.example.librarymanaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.adapter.SachAdapter;
import com.example.librarymanaapp.dao.SachDAO;
import com.example.librarymanaapp.model.Sach;

import java.util.List;


public class frgSach extends Fragment {
    ListView lstSach;
    SachDAO sachDAO;
    SachAdapter adapter;
    Sach item;
    List<Sach> list;
    public frgSach() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v = inflater.inflate(R.layout.fragment_sach, container, false);
       lstSach = v.findViewById(R.id.lstSach);
       sachDAO = new SachDAO(getActivity());
       capNhatLst();
       return v;
    }
    void capNhatLst(){
        list = (List<Sach>) sachDAO.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        lstSach.setAdapter(adapter);
    }
}