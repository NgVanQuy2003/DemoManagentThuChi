package com.example.admin.demomanagentthuchi.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.demomanagentthuchi.Model.Mangement_loaiChi;
import com.example.admin.demomanagentthuchi.R;

import java.util.ArrayList;

public class Adapter_spinnerLoaiChi extends BaseAdapter {
    Context c;
    ArrayList<Mangement_loaiChi> listLoaiChi=new ArrayList<Mangement_loaiChi>();

    public Adapter_spinnerLoaiChi(Context c, ArrayList<Mangement_loaiChi> listLoaiChi) {
        this.c = c;
        this.listLoaiChi = listLoaiChi;
    }

    @Override
    public int getCount() {
        return listLoaiChi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.one_item_sp_loaichi,null);

        TextView tv_item_sp_loaichi=convertView.findViewById(R.id.tv_item_sp_loaichi);

        Mangement_loaiChi loaiChi=listLoaiChi.get(position);

        tv_item_sp_loaichi.setText(loaiChi.tenLoaiChi);


        return convertView;
    }
}
