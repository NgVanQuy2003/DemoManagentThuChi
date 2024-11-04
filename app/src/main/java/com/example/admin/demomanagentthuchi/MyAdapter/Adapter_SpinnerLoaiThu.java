package com.example.admin.demomanagentthuchi.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.demomanagentthuchi.Model.Management_loaiThu;
import com.example.admin.demomanagentthuchi.R;

import java.util.ArrayList;

public class Adapter_SpinnerLoaiThu extends BaseAdapter {
    Context c;
    ArrayList<Management_loaiThu> listLoaiThu1 = new ArrayList<Management_loaiThu>();

    public Adapter_SpinnerLoaiThu(Context c, ArrayList<Management_loaiThu> listLoaiThu1) {
        this.c = c;
        this.listLoaiThu1 = listLoaiThu1;
    }
    @Override
    public int getCount() {
        return listLoaiThu1.size();
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
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        convertView = inflater.inflate(R.layout.one_item_sp_loaithu, null);

        TextView tv_item_sp_loaithu = convertView.findViewById(R.id.tv_item_sp_loaithu);

        Management_loaiThu loaiThu = listLoaiThu1.get(position);
        tv_item_sp_loaithu.setText(loaiThu.tenLoaiThu);

        return convertView;
    }
}
