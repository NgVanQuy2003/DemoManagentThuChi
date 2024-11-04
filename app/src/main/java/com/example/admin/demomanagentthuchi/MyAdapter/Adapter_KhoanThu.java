package com.example.admin.demomanagentthuchi.MyAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.example.admin.demomanagentthuchi.KhoanChiFragment;
import com.example.admin.demomanagentthuchi.KhoanThuFragment;
import com.example.admin.demomanagentthuchi.Model.Management_khoanThu;
import com.example.admin.demomanagentthuchi.Model.Management_loaiThu;
import com.example.admin.demomanagentthuchi.Model.Mangement_loaiChi;
import com.example.admin.demomanagentthuchi.R;
import com.example.admin.demomanagentthuchi.SQLite.khoanThuDAO;
import com.example.admin.demomanagentthuchi.SQLite.loaiChiDAO;
import com.example.admin.demomanagentthuchi.SQLite.loaiThuDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class Adapter_KhoanThu extends RecyclerView.Adapter<Adapter_KhoanThu.MyViewHolder> implements Filterable {


    Context context;
    ArrayList<Management_khoanThu> listKhoanThu = new ArrayList<Management_khoanThu>();
    ArrayList<Management_loaiThu> listLoaiThu = new ArrayList<Management_loaiThu>();
    KhoanThuFragment khoanThuFragment;

    ArrayList<Management_khoanThu> listSearch;


    public Adapter_KhoanThu(Context context, ArrayList<Management_khoanThu> listKhoanThu, ArrayList<Management_loaiThu> listLoaiThu, KhoanThuFragment khoanThuFragment) {
        this.context = context;
        this.listKhoanThu = listKhoanThu;
        this.listLoaiThu = listLoaiThu;
        this.khoanThuFragment = khoanThuFragment;
        listSearch=new ArrayList<>(listKhoanThu);
    }

    String date;
    Spinner sp_EditLoaiThu;
    loaiThuDAO loaiThuDAO;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.one_item_for_khoan_thu, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Management_khoanThu khoanThu = listKhoanThu.get(position);

        holder.tv_tenKhoanThu.setText(khoanThu.tenKhoanThu);
        holder.tv_ngayThu.setText(khoanThu.ngayThu);

        String soTien = khoanThu.noiDung;
        String mangTien[] = soTien.split("");
        // split dùng để chia chuỗi
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
        holder.tv_SoTienThu.setText(EpKieuThanhCong + " vnđ");

        String tenLoaiThu = null;
        loaiThuDAO = new loaiThuDAO(khoanThuFragment.getContext());
        listLoaiThu = loaiThuDAO.ViewLoaiThu();
        for (Management_loaiThu loaiThu : listLoaiThu) {
            if (loaiThu._id == khoanThu._idLoaiThu) {
                tenLoaiThu = loaiThu.tenLoaiThu;

            }
        }
        holder.tv_LoaiThu.setText(tenLoaiThu);


        holder.iv_DeleteKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AwesomeErrorDialog(khoanThuFragment.getContext())
                        .setTitle("Bạn có chắc muốn xóa")
                        .setMessage(" Dữ liệu sẽ không thể phục hồi!")
                        .setColoredCircle(R.color.dialogErrorBackgroundColor)
                        .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                        .setCancelable(true).setButtonText("OK")
                        .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                        .setCancelable(true)
                        .setErrorButtonClick(new Closure() {
                            @Override
                            public void exec() {
                                Management_khoanThu khoanThu1 = listKhoanThu.get(position);
                                int _id = khoanThu1._id;
                                khoanThuDAO khoanThuDAO = new khoanThuDAO(khoanThuFragment.getContext());
                                khoanThuDAO.DeleteKhoanThu(_id);
                                khoanThuFragment.CapNhatGiaoDienKhoanThu();
                                Toasty.success(khoanThuFragment.getContext(), "Đã xóa!", Toast.LENGTH_SHORT, true).show();
                            }
                        })
                        .setCancelable(true)
                        .show();

            }
        });
        holder.iv_editKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dialog
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(khoanThuFragment.getContext());
                LayoutInflater inf = khoanThuFragment.getActivity().getLayoutInflater();
                View view1 = inf.inflate(R.layout.dialog_for_edit_khoan_thu, null);

                ///Lay du lieu va setText
                final Management_khoanThu khoanThu = listKhoanThu.get(position);
                final EditText et_EditKhoanThu = view1.findViewById(R.id.et_EditKhoanThu);
                final EditText et_EditNoidungKhoanThu = view1.findViewById(R.id.et_EditNoidungKhoanThu);


                //spinner
                sp_EditLoaiThu = view1.findViewById(R.id.sp_EditLoaiThu);
                loaiThuDAO = new loaiThuDAO(view1.getContext());
                CapNhatGiaoDienSpinnerKhoanThu();
                //Spinner

                //date
                CalendarView EditcalendarView = view1.findViewById(R.id.EditcalendarView);
                EditcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    }
                });

                //set date view in calendar
                EditcalendarView.setSelected(true);
                try {
                    String getDate = khoanThu.ngayThu;
                    String MangDate[] = getDate.split("/");
                    int day = Integer.parseInt(MangDate[0]);
                    int month = Integer.parseInt(MangDate[1]);
                    int year = Integer.parseInt(MangDate[2]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, (month - 1));
                    calendar.set(Calendar.DAY_OF_MONTH, day);

                    long milliTime = calendar.getTimeInMillis();
                    EditcalendarView.setDate(milliTime, true, true);
                } catch (Exception e) {

                }


                //Spinner
                int IndexSpinner = 0;
                for (int i = 0; i <= listLoaiThu.size() - 1; i++) {
                    if (khoanThu._idLoaiThu == listLoaiThu.get(i)._id) {
                        IndexSpinner = i;
                    }
                }
                sp_EditLoaiThu.setSelection(IndexSpinner);
                et_EditKhoanThu.setText(khoanThu.tenKhoanThu);
                et_EditNoidungKhoanThu.setText(khoanThu.noiDung);


                ///Lay du lieu va setText

                alertDialog.setView(view1);

                alertDialog.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //String
                        String tenKhoanThu = et_EditKhoanThu.getText().toString();
                        String noiDung = et_EditNoidungKhoanThu.getText().toString();
                        //String


                        //sp
                        int _idLoaiThu = 0;
                        try {
                            int index = sp_EditLoaiThu.getSelectedItemPosition();
                            Management_loaiThu loaiThu = listLoaiThu.get(index);
                            _idLoaiThu = loaiThu._id;
                        } catch (Exception e) {

                        }
                        //sp

                        //date
                        String getDate = date;
                        try {
                            if (getDate == null) {
                                getDate = khoanThu.ngayThu;
                            }

                        } catch (Exception e) {

                        }
                        //date


                        //SQLite
                        Management_khoanThu khoanThu = listKhoanThu.get(position);
                        Management_khoanThu khoanThu1 = new Management_khoanThu(khoanThu._id, tenKhoanThu, noiDung, getDate, _idLoaiThu);
                        khoanThuDAO thuDAO = new khoanThuDAO(khoanThuFragment.getContext());
                        thuDAO.UpdateKhoanThu(khoanThu1);
                        //SQLite
                        khoanThuFragment.CapNhatGiaoDienKhoanThu();
                        Toasty.success(khoanThuFragment.getContext(), "Đã cập nhật!", Toast.LENGTH_SHORT, true).show();

                    }
                });
                alertDialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.error(khoanThuFragment.getContext(), "Đã hủy", Toast.LENGTH_SHORT, true).show();
                    }
                });
                alertDialog.show();
                //dialog
            }
        });


    }

    @Override
    public int getItemCount() {
        return listKhoanThu.size();
    }

    @Override
    public Filter getFilter() {
        return filterList;
    }
    //........search
    private Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Management_khoanThu> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listSearch);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Management_khoanThu item : listSearch) {
                    if (item.tenKhoanThu.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listKhoanThu.clear();
            listKhoanThu.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    //.......search


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tenKhoanThu, tv_SoTienThu, tv_LoaiThu, tv_ngayThu;
        public ImageView iv_editKhoanThu, iv_DeleteKhoanThu;

        public MyViewHolder(View view) {
            super(view);
            this.tv_tenKhoanThu = view.findViewById(R.id.tv_tenKhoanThu);
            this.tv_SoTienThu = view.findViewById(R.id.tv_SoTienThu);
            this.tv_LoaiThu = view.findViewById(R.id.tv_LoaiThu);
            this.tv_ngayThu = view.findViewById(R.id.tv_ngayThu);

            this.iv_DeleteKhoanThu = view.findViewById(R.id.iv_DeleteKhoanThu);
            this.iv_editKhoanThu = view.findViewById(R.id.iv_editKhoanThu);

        }
    }

    public void CapNhatGiaoDienSpinnerKhoanThu() {
        listLoaiThu = loaiThuDAO.ViewLoaiThu();
        //tạo một đối tượng Adapter_SpinnerLoaiThu mới để kết nối dữ liệu (danh sách loại thu) với giao diện của Spinner.
        Adapter_SpinnerLoaiThu spinnerLoaiThu = new Adapter_SpinnerLoaiThu(khoanThuFragment.getContext(), listLoaiThu);
        sp_EditLoaiThu.setAdapter(spinnerLoaiThu); // Dòng này thiết lập Adapter cho Spinner sp_EditLoaiThu
    }

}
