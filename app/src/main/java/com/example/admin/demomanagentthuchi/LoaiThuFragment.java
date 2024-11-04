package com.example.admin.demomanagentthuchi;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.demomanagentthuchi.Model.Management_loaiThu;
import com.example.admin.demomanagentthuchi.MyAdapter.AdapterLoaiThu;
import com.example.admin.demomanagentthuchi.SQLite.loaiThuDAO;
import com.sdsmdg.tastytoast.TastyToast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiThuFragment extends Fragment {

    //Dialog and floatactionbutton
    FloatingActionButton addLoaiThu;
    EditText et_AddLoaiThu;
    //SwipeRefreshLayout swipeRefreshLayout;
    //Dialog and floatActionButton

    //RecyclerView
    RecyclerView recyclerViewLoaiThu;
    ArrayList<Management_loaiThu> listLoaiThu = new ArrayList<Management_loaiThu>();
    //RecyclerView

    //search
    private SearchView searchView;
    private SearchView.OnQueryTextListener queryTextListener;
    AdapterLoaiThu adapter;
    View view;
    //search

    loaiThuDAO loaiThuDAO;
    Management_loaiThu loaiThu;

    public LoaiThuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loai_thu, null);

        //refresh
        //refresh();
        //refresh

        //add
        addData();
        //add

        //RecycleView
        capnhatGiaodienLoaiThu();
        //Recycle view

        return view;
    }

    //have to import this void new
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    // have to import this void.


    // Create option menu for toolbar equal lookup, Just use for fragment
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Have to add Clear with tabLayout
        menu.clear();
        //Have to add Clear with tablayou
        inflater.inflate(R.menu.menu_for_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    //important
                    adapter.getFilter().filter(newText);
                    //important

                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
    //create option menu for toolbar equal lookup, Just use for fragment


    public void addData() {
        //Even action button view dialog
        addLoaiThu = view.findViewById(R.id.addLoaiThu);
        addLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                LayoutInflater inf = LoaiThuFragment.this.getLayoutInflater();
                View view1 = inf.inflate(R.layout.dialog_one, null);
 // anh
                et_AddLoaiThu = view1.findViewById(R.id.et_AddLoaiThu);

                alertDialog.setView(view1);

                alertDialog.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tenLoaiThu = et_AddLoaiThu.getText().toString();

                        //SQLite
                        loaiThu = new Management_loaiThu(tenLoaiThu);
                        loaiThuDAO = new loaiThuDAO(getContext());
                        loaiThuDAO.AddLoaiThu(loaiThu);
                        //SQLite
                        capnhatGiaodienLoaiThu();
                        Toasty.success(getContext(), "Đã thêm!", Toast.LENGTH_SHORT, true).show();
                        //TastyToast.makeText(getContext(), "Đ !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    }
                });
                alertDialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.error(getContext(), "Đã hủy", Toast.LENGTH_SHORT, true).show();
                    }
                });
                alertDialog.show();
                //dialog

            }
        });
        //Even action button view dialog
    }

    public void capnhatGiaodienLoaiThu() {
        recyclerViewLoaiThu = view.findViewById(R.id.recyclerView);
        loaiThuDAO = new loaiThuDAO(view.getContext());
        //View Loai Thu
        listLoaiThu = loaiThuDAO.ViewLoaiThu();
        //View Loai Thu
        recyclerViewLoaiThu.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new AdapterLoaiThu(listLoaiThu, getContext(), this);

        recyclerViewLoaiThu.setLayoutManager(linearLayoutManager);
        recyclerViewLoaiThu.setAdapter(adapter);
    }

//    public void refresh() {
//        //refreshLayout use support v4
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        capnhatGiaodienLoaiThu();
//                        //Toasty.success(getContext(), " Đã cập nhật! ", Toast.LENGTH_SHORT, true).show();
//                        TastyToast.makeText(getContext(), "Hoàn tất !", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
//        //refreshLayout
//    }



}
