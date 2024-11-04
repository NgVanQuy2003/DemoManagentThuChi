package com.example.admin.demomanagentthuchi;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.demomanagentthuchi.Model.Management_khoanChi;
import com.example.admin.demomanagentthuchi.Model.Management_khoanThu;
import com.example.admin.demomanagentthuchi.Model.Management_loaiThu;
import com.example.admin.demomanagentthuchi.Model.Mangement_loaiChi;
import com.example.admin.demomanagentthuchi.SQLite.khoanChiDAO;
import com.example.admin.demomanagentthuchi.SQLite.khoanThuDAO;
import com.example.admin.demomanagentthuchi.SQLite.loaiChiDAO;
import com.example.admin.demomanagentthuchi.SQLite.loaiThuDAO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeFragmentHaiFragment extends Fragment {

//    PieChart pieChart2;
    TextView tv_TongThu, tv_tongLoaiThu;
    ArrayList<Management_khoanThu> listKhoanThu;
    khoanThuDAO khoanThuDAO;
    ArrayList<Management_loaiThu> listLoaiThu;

    public ThongKeFragmentHaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_fragment_hai, null);


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
        //Thiet lap du lieu tu sqlite
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        pieChart2 = view.findViewById(R.id.piechart2);
        tv_tongLoaiThu = view.findViewById(R.id.tv_tongTungLoaiThu);

        //Khai báo xử lý dữ liệu bằng IQ cấp cao của developer
        String TongT1 = "";
        String bienDoi1 = "";
        String mangCatTiepTuc1[];
        //Khai báo xử lý dữ liệu bằng IQ cấp cao của developer

        //ThietLapHienThiPhanTram
//        pieChart2.setUsePercentValues(true);
        //ThietLapHienThiPhanTram

        //xu ly data
        listLoaiThu = new ArrayList<Management_loaiThu>();
        loaiThuDAO loaiThuDAO = new loaiThuDAO(getContext());
        listLoaiThu = loaiThuDAO.ViewLoaiThu();
        //xu ly data

        //Xu ly tinh tong tung loai thu
        long mangGan[] = new long[listLoaiThu.size()];
        try {
            for (int i = 0; i < listLoaiThu.size(); i++) {
                for (int j = 0; j < listKhoanThu.size(); j++) {
                    if (listLoaiThu.get(i)._id == listKhoanThu.get(j)._idLoaiThu) {
                        long ganCho = Long.parseLong(listKhoanThu.get(j).noiDung);
                        mangGan[i] += ganCho;
                    }
                }

                //Xu ly textView bằng IQ Cấp cao của developer
                bienDoi1 += "-" + mangGan[i];
                String mangCat1[] = bienDoi1.split("-");
                //Toast.makeText(getContext(), "Mảng đã cắt: " + mangCat[i + 1], Toast.LENGTH_SHORT).show();
                mangCatTiepTuc1 = mangCat1[i + 1].split("");
                String chuoiChua1 = "";
                if (mangCat1[i + 1].length() == 1) {
                    chuoiChua1 += mangCatTiepTuc1[1];
                }
                if (mangCat1[i + 1].length() == 2) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2];
                }
                if (mangCat1[i + 1].length() == 3) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + mangCatTiepTuc1[3];
                }
                if (mangCat1[i + 1].length() == 4) {
                    chuoiChua1 += mangCatTiepTuc1[1] + "." + mangCatTiepTuc1[2] + mangCatTiepTuc1[3] + mangCatTiepTuc1[4];
                }
                if (mangCat1[i + 1].length() == 5) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + "." + mangCatTiepTuc1[3] + mangCatTiepTuc1[4] + mangCatTiepTuc1[5];
                }
                if (mangCat1[i + 1].length() == 6) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + mangCatTiepTuc1[3] + "." + mangCatTiepTuc1[4] + mangCatTiepTuc1[5] + mangCatTiepTuc1[6];
                }
                if (mangCat1[i + 1].length() == 7) {
                    chuoiChua1 += mangCatTiepTuc1[1] + "." + mangCatTiepTuc1[2] + mangCatTiepTuc1[3] + mangCatTiepTuc1[4] + "." + mangCatTiepTuc1[5] + mangCatTiepTuc1[6] + mangCatTiepTuc1[7];
                }
                if (mangCat1[i + 1].length() == 8) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + "." + mangCatTiepTuc1[3] + mangCatTiepTuc1[4] + mangCatTiepTuc1[5] + "." + mangCatTiepTuc1[6] + mangCatTiepTuc1[7] + mangCatTiepTuc1[8];
                }
                if (mangCat1[i + 1].length() == 9) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + mangCatTiepTuc1[3] + "." + mangCatTiepTuc1[4] + mangCatTiepTuc1[5] + mangCatTiepTuc1[6] + "." + mangCatTiepTuc1[7] + mangCatTiepTuc1[8] + mangCatTiepTuc1[9];
                }
                if (mangCat1[i + 1].length() == 10) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + mangCatTiepTuc1[3] + mangCatTiepTuc1[4] + "." + mangCatTiepTuc1[5] + mangCatTiepTuc1[6] + mangCatTiepTuc1[7] + "." + mangCatTiepTuc1[8] + mangCatTiepTuc1[9] + mangCatTiepTuc1[10];
                }
                if (mangCat1[i + 1].length() == 11) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + "." + mangCatTiepTuc1[3] + mangCatTiepTuc1[4] + mangCatTiepTuc1[5] + "." + mangCatTiepTuc1[6] + mangCatTiepTuc1[7] + mangCatTiepTuc1[8] + "." + mangCatTiepTuc1[9] + mangCatTiepTuc1[10] + mangCatTiepTuc1[11];
                }
                if (mangCat1[i + 1].length() == 12) {
                    chuoiChua1 += mangCatTiepTuc1[1] + mangCatTiepTuc1[2] + mangCatTiepTuc1[3] + "." + mangCatTiepTuc1[4] + mangCatTiepTuc1[5] + mangCatTiepTuc1[6] + "." + mangCatTiepTuc1[7] + mangCatTiepTuc1[8] + mangCatTiepTuc1[9] + "." + mangCatTiepTuc1[10] + mangCatTiepTuc1[11] + mangCatTiepTuc1[12];
                }

                TongT1 += "- " + listLoaiThu.get(i).tenLoaiThu + ": " + chuoiChua1 + " vnđ" + "\n";
                tv_tongLoaiThu.setText(TongT1);

                //Xử lý dữ liệu textView Bằng IQ cấp cao cửa developer

            }
        } catch (Exception e) {

        }

//        ///List one
//        ArrayList<Entry> yvaluesT = new ArrayList<Entry>();
//
//        try {
//
//            for (int k = 0; k < listLoaiThu.size(); k++) {
//                yvaluesT.add(new Entry((mangGan[k] * 100) / tongThu, k));
//            }
//        } catch (Exception e) {
//
//        }
//
////        PieDataSet dataSet1 = new PieDataSet(yvaluesT, "");
//        ///list One
//
//        ///list two
//        ArrayList<String> xVals1 = new ArrayList<String>();
//        try {
//            for (int k = 0; k < listLoaiThu.size(); k++) {
//                xVals1.add(listLoaiThu.get(k).tenLoaiThu);
//            }
//        } catch (Exception e) {
//
//        }

//        PieData data1 = new PieData(xVals1, dataSet1);
        ///list two

//        data1.setValueFormatter(new PercentFormatter());

//        pieChart2.setData(data1);
        //Set color
//        dataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        //dataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        //dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        //set color

//        pieChart2.setDescription("Biểu đồ tròn tổng từng loại thu");

        //text size and text color
//        data1.setValueTextSize(13f);
//        data1.setValueTextColor(Color.WHITE);
        //text size and text color

        //animation
//        pieChart2.animateXY(1500, 1500);
        //animationx`


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        BarChart barChart =view.findViewById(R.id.barchart);
//
        ArrayList<String> labels = new ArrayList<String>();
        try {
            for (int k = 0; k < listLoaiThu.size(); k++) {
                labels.add(listLoaiThu.get(k).tenLoaiThu);
            }
        } catch (Exception e) {

        }
//
//        // create BarEntry for Bar Group 1
//        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
//        try {
//
//            for (int k = 0; k < listLoaiThu.size(); k++) {
//                bargroup1.add(new BarEntry((mangGan[k] * 100) / tongThu, k));
//            }
//        } catch (Exception e) {
//
//        }

//        BarDataSet bardataset = new BarDataSet(bargroup1, "Loại thu");

//        BarData data = new BarData(labels, bardataset);
//        barChart.setData(data); // set the data and list of labels into chart
//        barChart.setDescription("Tỉ lệ % từng loại thu");  // set the description
//        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        barChart.animateY(5000);




        return view;
    }

}
