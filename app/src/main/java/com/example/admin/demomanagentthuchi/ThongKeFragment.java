package com.example.admin.demomanagentthuchi;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.demomanagentthuchi.Model.Management_khoanChi;
import com.example.admin.demomanagentthuchi.Model.Management_khoanThu;
import com.example.admin.demomanagentthuchi.Model.Management_loaiThu;
import com.example.admin.demomanagentthuchi.Model.Mangement_loaiChi;
import com.example.admin.demomanagentthuchi.SQLite.khoanChiDAO;
import com.example.admin.demomanagentthuchi.SQLite.khoanThuDAO;
import com.example.admin.demomanagentthuchi.SQLite.loaiChiDAO;
import com.example.admin.demomanagentthuchi.SQLite.loaiThuDAO;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeFragment extends Fragment {

//    PieChart piechart;
    TextView tv_TongThu, tv_TongChi;
    ArrayList<Management_khoanThu> listKhoanThu;
    khoanThuDAO khoanThuDAO;
    ArrayList<Management_khoanChi> listKhoanChi;
    khoanChiDAO khoanChiDAO;



    public ThongKeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thong_ke, null);

        long tongChi = 0;
        long tongThu = 0;

        //Thiet lap du lieu tu sqlite
        listKhoanThu = new ArrayList<Management_khoanThu>();
        khoanThuDAO = new khoanThuDAO(getContext());
        listKhoanThu = khoanThuDAO.ViewKhoanThu();
        try {
            for (Management_khoanThu khoanThu : listKhoanThu) {
                long epdulieukt = Long.parseLong(khoanThu.noiDung);
                tongThu += epdulieukt;
            }

        } catch (Exception e) {
        }


        listKhoanChi = new ArrayList<Management_khoanChi>();
        khoanChiDAO = new khoanChiDAO(getContext());
        listKhoanChi = khoanChiDAO.ViewKhoanChi();
        try {
            for (Management_khoanChi khoanChi : listKhoanChi) {
                long epdulieukc = Long.parseLong(khoanChi.noiDung);
                tongChi += epdulieukc;
            }

        } catch (Exception e) {
        }

        tv_TongThu = view.findViewById(R.id.tv_TongThu);
        String soTien = String.valueOf(tongThu);
        String mangTien[] = soTien.split("");
        String EpKieuThanhCong = "";
        if (soTien.length() >= 1) {
            EpKieuThanhCong = mangTien[1];
        }
        if (soTien.length() >= 2) {
            EpKieuThanhCong = mangTien[1] + mangTien[2];
        }
        if (soTien.length() >= 3) {
            EpKieuThanhCong = mangTien[1] + mangTien[2] + mangTien[3];
        }
        if (soTien.length() >= 4) {
            EpKieuThanhCong = mangTien[1] + "." + mangTien[2] + mangTien[3] + mangTien[4];
        }
        if (soTien.length() >= 5) {
            EpKieuThanhCong = mangTien[1] + mangTien[2] + "." + mangTien[3] + mangTien[4] + mangTien[5];
        }
        if (soTien.length() >= 6) {
            EpKieuThanhCong = mangTien[1] + mangTien[2] + mangTien[3] + "." + mangTien[4] + mangTien[5] + mangTien[6];
        }
        if (soTien.length() >= 7) {
            EpKieuThanhCong = mangTien[1] + "." + mangTien[2] + mangTien[3] + mangTien[4] + "." + mangTien[5] + mangTien[6] + mangTien[7];
        }
        if (soTien.length() >= 8) {
            EpKieuThanhCong = mangTien[1] + mangTien[2] + "." + mangTien[3] + mangTien[4] + mangTien[5] + "." + mangTien[6] + mangTien[7] + mangTien[8];
        }
        if (soTien.length() >= 9) {
            EpKieuThanhCong = mangTien[1] + mangTien[2] + mangTien[3] + "." + mangTien[4] + mangTien[5] + mangTien[6] + "." + mangTien[7] + mangTien[8] + mangTien[9];
        }
        if (soTien.length() >= 10) {
            EpKieuThanhCong = mangTien[1] + "." + mangTien[2] + mangTien[3] + mangTien[4] + "." + mangTien[5] + mangTien[6] + mangTien[7] + "." + mangTien[8] + mangTien[9] + mangTien[10];
        }
        if (soTien.length() >= 11) {
            EpKieuThanhCong = mangTien[1] + mangTien[2] + "." + mangTien[3] + mangTien[4] + mangTien[5] + "." + mangTien[6] + mangTien[7] + mangTien[8] + "." + mangTien[9] + mangTien[10] + mangTien[11];
        }
        if (soTien.length() >= 12) {
            EpKieuThanhCong = mangTien[1] + mangTien[2] + mangTien[3] + "." + mangTien[4] + mangTien[5] + mangTien[6] + "." + mangTien[7] + mangTien[8] + mangTien[9] + "." + mangTien[10] + mangTien[11] + mangTien[12];
        }
        tv_TongThu.setText("- Tổng thu: " + EpKieuThanhCong + " vnđ");


//////////////////////////////////////////////////////////////////
        tv_TongChi = view.findViewById(R.id.tv_TongChi);
        String soTien1 = String.valueOf(tongChi);
        String mangTien1[] = soTien1.split("");
        String EpKieuThanhCong1 = "";
        if (soTien1.length() >= 1) {
            EpKieuThanhCong1 = mangTien1[1];
        }
        if (soTien1.length() >= 2) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2];
        }
        if (soTien1.length() >= 3) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2] + mangTien1[3];
        }
        if (soTien1.length() >= 4) {
            EpKieuThanhCong1 = mangTien1[1] + "." + mangTien1[2] + mangTien1[3] + mangTien1[4];
        }
        if (soTien1.length() >= 5) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2] + "." + mangTien1[3] + mangTien1[4] + mangTien1[5];
        }
        if (soTien1.length() >= 6) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2] + mangTien1[3] + "." + mangTien1[4] + mangTien1[5] + mangTien1[6];
        }
        if (soTien1.length() >= 7) {
            EpKieuThanhCong1 = mangTien1[1] + "." + mangTien1[2] + mangTien1[3] + mangTien1[4] + "." + mangTien1[5] + mangTien1[6] + mangTien1[7];
        }
        if (soTien1.length() >= 8) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2] + "." + mangTien1[3] + mangTien1[4] + mangTien1[5] + "." + mangTien1[6] + mangTien1[7] + mangTien1[8];
        }
        if (soTien1.length() >= 9) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2] + mangTien1[3] + "." + mangTien1[4] + mangTien1[5] + mangTien1[6] + "." + mangTien1[7] + mangTien1[8] + mangTien1[9];
        }
        if (soTien1.length() >= 10) {
            EpKieuThanhCong1 = mangTien1[1] + "." + mangTien1[2] + mangTien1[3] + mangTien1[4] + "." + mangTien1[5] + mangTien1[6] + mangTien1[7] + "." + mangTien1[8] + mangTien1[9] + mangTien1[10];
        }
        if (soTien1.length() >= 11) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2] + "." + mangTien1[3] + mangTien1[4] + mangTien1[5] + "." + mangTien1[6] + mangTien1[7] + mangTien1[8] + "." + mangTien1[9] + mangTien1[10] + mangTien1[11];
        }
        if (soTien1.length() >= 12) {
            EpKieuThanhCong1 = mangTien1[1] + mangTien1[2] + mangTien1[3] + "." + mangTien1[4] + mangTien1[5] + mangTien1[6] + "." + mangTien1[7] + mangTien1[8] + mangTien1[9] + "." + mangTien1[10] + mangTien1[11] + mangTien1[12];
        }

        tv_TongChi.setText("- Tổng chi: " + EpKieuThanhCong1 + " vnđ");

        return view;
    }

}
