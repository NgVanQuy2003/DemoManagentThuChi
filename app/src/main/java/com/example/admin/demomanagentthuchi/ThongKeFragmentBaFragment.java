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
public class ThongKeFragmentBaFragment extends Fragment {

//    PieChart  pieChart3;
    TextView  tv_TongChi, tv_TongLoaiChi;
    ArrayList<Management_khoanChi> listKhoanChi;
    khoanChiDAO khoanChiDAO;

    ArrayList<Mangement_loaiChi> listLoaiChi;


    public ThongKeFragmentBaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_thong_ke_fragment_ba,null);
        long tongChi = 0;

        //Thiet lap du lieu tu sqlite

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

        //Thiet lap du lieu tu sqlite

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        pieChart3 = view.findViewById(R.id.piechart3);
        tv_TongLoaiChi = view.findViewById(R.id.tv_tongTungLoaiChi);

        //Khai báo xử lý dữ liệu bằng IQ cấp cao của developer
        String TongC = "";
        String bienDoi2 = "";
        String mangCatTiepTuc2[];
        //Khai báo xử lý dữ liệu bằng IQ cấp cao của developer

        //ThietLapHienThiPhanTram
//        pieChart3.setUsePercentValues(true);
        //ThietLapHienThiPhanTram

        //xu ly data
        listLoaiChi = new ArrayList<Mangement_loaiChi>();
        loaiChiDAO loaiChiDAO = new loaiChiDAO(getContext());
        listLoaiChi = loaiChiDAO.ViewLoaiChi();
        //xu ly data

        //Xu ly tinh tong tung loai thu
        long mangGan1[] = new long[listLoaiChi.size()];
        try {
            for (int i = 0; i < listLoaiChi.size(); i++) {
                for (int j = 0; j < listKhoanChi.size(); j++) {
                    if (listLoaiChi.get(i)._id == listKhoanChi.get(j)._idLoaiChi) {
                        long ganCho = Long.parseLong(listKhoanChi.get(j).noiDung);
                        mangGan1[i] += ganCho;
                    }
                }

                //Xu ly textView bằng IQ Cấp cao của developer
                bienDoi2 += "-" + mangGan1[i];
                String mangCat2[] = bienDoi2.split("-");
                //Toast.makeText(getContext(), "Mảng đã cắt: " + mangCat[i + 1], Toast.LENGTH_SHORT).show();
                mangCatTiepTuc2 = mangCat2[i + 1].split("");
                String chuoiChua2 = "";
                if (mangCat2[i + 1].length() == 1) {
                    chuoiChua2 += mangCatTiepTuc2[1];
                }
                if (mangCat2[i + 1].length() == 2) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2];
                }
                if (mangCat2[i + 1].length() == 3) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + mangCatTiepTuc2[3];
                }
                if (mangCat2[i + 1].length() == 4) {
                    chuoiChua2 += mangCatTiepTuc2[1] + "." + mangCatTiepTuc2[2] + mangCatTiepTuc2[3] + mangCatTiepTuc2[4];
                }
                if (mangCat2[i + 1].length() == 5) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + "." + mangCatTiepTuc2[3] + mangCatTiepTuc2[4] + mangCatTiepTuc2[5];
                }
                if (mangCat2[i + 1].length() == 6) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + mangCatTiepTuc2[3] + "." + mangCatTiepTuc2[4] + mangCatTiepTuc2[5] + mangCatTiepTuc2[6];
                }
                if (mangCat2[i + 1].length() == 7) {
                    chuoiChua2 += mangCatTiepTuc2[1] + "." + mangCatTiepTuc2[2] + mangCatTiepTuc2[3] + mangCatTiepTuc2[4] + "." + mangCatTiepTuc2[5] + mangCatTiepTuc2[6] + mangCatTiepTuc2[7];
                }
                if (mangCat2[i + 1].length() == 8) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + "." + mangCatTiepTuc2[3] + mangCatTiepTuc2[4] + mangCatTiepTuc2[5] + "." + mangCatTiepTuc2[6] + mangCatTiepTuc2[7] + mangCatTiepTuc2[8];
                }
                if (mangCat2[i + 1].length() == 9) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + mangCatTiepTuc2[3] + "." + mangCatTiepTuc2[4] + mangCatTiepTuc2[5] + mangCatTiepTuc2[6] + "." + mangCatTiepTuc2[7] + mangCatTiepTuc2[8] + mangCatTiepTuc2[9];
                }
                if (mangCat2[i + 1].length() == 10) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + mangCatTiepTuc2[3] + mangCatTiepTuc2[4] + "." + mangCatTiepTuc2[5] + mangCatTiepTuc2[6] + mangCatTiepTuc2[7] + "." + mangCatTiepTuc2[8] + mangCatTiepTuc2[9] + mangCatTiepTuc2[10];
                }
                if (mangCat2[i + 1].length() == 11) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + "." + mangCatTiepTuc2[3] + mangCatTiepTuc2[4] + mangCatTiepTuc2[5] + "." + mangCatTiepTuc2[6] + mangCatTiepTuc2[7] + mangCatTiepTuc2[8] + "." + mangCatTiepTuc2[9] + mangCatTiepTuc2[10] + mangCatTiepTuc2[11];
                }
                if (mangCat2[i + 1].length() == 12) {
                    chuoiChua2 += mangCatTiepTuc2[1] + mangCatTiepTuc2[2] + mangCatTiepTuc2[3] + "." + mangCatTiepTuc2[4] + mangCatTiepTuc2[5] + mangCatTiepTuc2[6] + "." + mangCatTiepTuc2[7] + mangCatTiepTuc2[8] + mangCatTiepTuc2[9] + "." + mangCatTiepTuc2[10] + mangCatTiepTuc2[11] + mangCatTiepTuc2[12];
                }

                TongC += "- " + listLoaiChi.get(i).tenLoaiChi + ": " + chuoiChua2 + " vnđ" + "\n";
                tv_TongLoaiChi.setText(TongC);

                //Xử lý dữ liệu textView Bằng IQ cấp cao cửa developer

            }

        } catch (Exception e) {

        }

        ///List one
//        ArrayList<Entry> yvaluesC = new ArrayList<Entry>();
//
//        try {
//
//            for (int k = 0; k < listLoaiChi.size(); k++) {
//                yvaluesC.add(new Entry((mangGan1[k] * 100) / tongChi, k));
//            }
//        } catch (Exception e) {
//
//        }
//
//        PieDataSet dataSet2 = new PieDataSet(yvaluesC, "");
//        ///list One
//
//        ///list two
//        ArrayList<String> xVals2 = new ArrayList<String>();
//        try {
//            for (int k = 0; k < listLoaiChi.size(); k++) {
//                xVals2.add(listLoaiChi.get(k).tenLoaiChi);
//            }
//        } catch (Exception e) {
//
//        }
//
//        PieData data2 = new PieData(xVals2, dataSet2);
//        ///list two
//
//        data2.setValueFormatter(new PercentFormatter());
//
////        pieChart3.setData(data2);
//        //Set color
//        dataSet2.setColors(ColorTemplate.VORDIPLOM_COLORS);
//        //dataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
//        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        //dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//        //dataSet.setColors(ColorTemplate.PASTEL_COLORS);
//        //set color
////        pieChart3.setDescription("Biểu đồ tròn tổng từng loại chi");
//
//        //text size and text color
//        data2.setValueTextSize(13f);
//        data2.setValueTextColor(Color.DKGRAY);
//        //text size and text color
//
//        //animation
////        pieChart3.animateXY(1500, 1500);
//        //animation
//        //////////////////////////////////////////////////////////////////////////////////////
////        BarChart barChart =view.findViewById(R.id.barchart);
//
//        ArrayList<String> labels = new ArrayList<String>();
//        try {
//            for (int k = 0; k < listLoaiChi.size(); k++) {
//                labels.add(listLoaiChi.get(k).tenLoaiChi);
//            }
//        } catch (Exception e) {
//
//        }
//
//        // create BarEntry for Bar Group 1
//        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
//        try {
//
//            for (int k = 0; k < listLoaiChi.size(); k++) {
//                bargroup1.add(new BarEntry((mangGan1[k] * 100) / tongChi, k));
//            }
//        } catch (Exception e) {
//
//        }
//
////        BarDataSet bardataset = new BarDataSet(bargroup1, "Loại chi");
//
////        BarData data = new BarData(labels, bardataset);
////        barChart.setData(data); // set the data and list of labels into chart
////        barChart.setDescription("Tỉ lệ % từng loại chi");  // set the description
////        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
////        barChart.animateY(5000);




        return  view;
    }

}
