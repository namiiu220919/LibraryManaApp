package com.example.librarymanaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.adapter.TopAdapter;
import com.example.librarymanaapp.dao.ThongKeDAO;
import com.example.librarymanaapp.model.Top;

import java.util.ArrayList;

public class frgTop extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;

    public frgTop() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_top, container, false);
        lv = v.findViewById(R.id.lstTop);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>) thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return v;
    }
}