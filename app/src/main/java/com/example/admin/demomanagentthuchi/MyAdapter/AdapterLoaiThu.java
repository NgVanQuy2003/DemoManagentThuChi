package com.example.admin.demomanagentthuchi.MyAdapter;

import android.app.AlertDialog;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.example.admin.demomanagentthuchi.KhoanThuFragment;
import com.example.admin.demomanagentthuchi.LoaiThuFragment;
import com.example.admin.demomanagentthuchi.MainActivity;
import com.example.admin.demomanagentthuchi.Model.Management_loaiThu;
import com.example.admin.demomanagentthuchi.R;
import com.example.admin.demomanagentthuchi.SQLite.loaiThuDAO;
import com.example.admin.demomanagentthuchi.ThuFragment;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AdapterLoaiThu extends RecyclerView.Adapter<AdapterLoaiThu.MyViewHolder> implements Filterable {

    ArrayList<Management_loaiThu> listLoaiThu = new ArrayList<Management_loaiThu>();
    ArrayList<Management_loaiThu> listsearch;//search

    public Context context;
    LoaiThuFragment loaiThuFragment;


    public AdapterLoaiThu(ArrayList<Management_loaiThu> listLoaiThu, Context context, LoaiThuFragment loaiThuFragment) {
        this.listLoaiThu = listLoaiThu;
        this.context = context;
        this.loaiThuFragment = loaiThuFragment;
        listsearch = new ArrayList<>(listLoaiThu);//search
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.one_item_for_loai_thu, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Management_loaiThu loaiThu = listLoaiThu.get(position);
        holder.tv_ED.setText(loaiThu.tenLoaiThu);
        holder.editLoaiThu.setImageResource(R.drawable.edit);
        holder.deleteLoaiThu.setImageResource(R.drawable.delete);

        holder.deleteLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AwesomeErrorDialog(loaiThuFragment.getContext())
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
                                // click
                                Management_loaiThu loaiThu = listLoaiThu.get(position);
                                int _id = loaiThu._id;
                                loaiThuDAO loaiThuDAO = new loaiThuDAO(context);
                                loaiThuDAO.deleteLoaiThu(_id);
                                loaiThuFragment.capnhatGiaodienLoaiThu();
                                Toasty.success(loaiThuFragment.getContext(), "Đã xóa!", Toast.LENGTH_SHORT, true).show();
                                // click
                            }
                        })
                        .setCancelable(true)
                        .show();

            }
        });


        holder.editLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(loaiThuFragment.getContext());
                LayoutInflater inf = loaiThuFragment.getActivity().getLayoutInflater();
                View view1 = inf.inflate(R.layout.dialog_edit_loai_thu, null);

                ///Lay du lieu va setText
                Management_loaiThu loaiThu = listLoaiThu.get(position);
                final EditText et_EditLoaiThu = view1.findViewById(R.id.et_EditLoaiThu);
                et_EditLoaiThu.setText(loaiThu.tenLoaiThu);
                ///Lay du lieu va setText

                alertDialog.setView(view1);

                alertDialog.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tenLoaiThu = et_EditLoaiThu.getText().toString();

                        //SQLite
                        Management_loaiThu loaiThu = listLoaiThu.get(position);
                        Management_loaiThu loaiThu1 = new Management_loaiThu(loaiThu._id, tenLoaiThu);
                        loaiThuDAO loaiThuDAO = new loaiThuDAO(loaiThuFragment.getContext());
                        loaiThuDAO.updateLoaiThu(loaiThu1);
                        //SQLite
                        loaiThuFragment.capnhatGiaodienLoaiThu();
                        Toasty.success(loaiThuFragment.getContext(), "Đã cập nhật!", Toast.LENGTH_SHORT, true).show();

                    }
                });
                alertDialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.error(loaiThuFragment.getContext(), "Đã hủy", Toast.LENGTH_SHORT, true).show();
                    }
                });
                alertDialog.show();
                //dialog
            }
        });


    }

    @Override
    public int getItemCount() {
        return listLoaiThu.size();
    }

    @Override
    public Filter getFilter() {
        return filterList;
    }

    //........search
    private Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Management_loaiThu> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listsearch);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Management_loaiThu item : listsearch) {
                    if (item.tenLoaiThu.toLowerCase().contains(filterPattern)) {
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
            listLoaiThu.clear();
            listLoaiThu.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    //.......search


    //Class nay có thể để bên ngoài
     class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_ED;
        public ImageView editLoaiThu, deleteLoaiThu;

        public MyViewHolder(View view) {
            super(view);
            this.tv_ED = view.findViewById(R.id.tv_ED);
            this.deleteLoaiThu = view.findViewById(R.id.iv_DeleteLoaiThu);
            this.editLoaiThu = view.findViewById(R.id.iv_editLoaiThu);
        }
    }

}


