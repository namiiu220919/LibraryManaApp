package com.example.librarymanaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.librarymanaapp.R;
import com.example.librarymanaapp.fragment.frgTop;
import com.example.librarymanaapp.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    frgTop frg;
    private ArrayList<Top> list;
    TextView tvSach, tvSl;

    public TopAdapter(@NonNull Context context, frgTop frg, ArrayList<Top> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.frg = frg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top,null);
        }
        final Top item = list.get(position);
        if(item != null){
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: " + item.getTenSach());
            tvSl = v.findViewById(R.id.tvSl);
            tvSl.setText("Số lượng: " + item.getSoLuong());
        }
        return v;
    }
}
