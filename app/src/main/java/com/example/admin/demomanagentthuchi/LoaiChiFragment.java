package com.example.admin.demomanagentthuchi;


import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

import com.example.admin.demomanagentthuchi.Model.Mangement_loaiChi;
import com.example.admin.demomanagentthuchi.MyAdapter.AdapterLoaiChi;
import com.example.admin.demomanagentthuchi.MyAdapter.AdapterLoaiThu;
import com.example.admin.demomanagentthuchi.SQLite.loaiChiDAO;
import com.example.admin.demomanagentthuchi.SQLite.loaiThuDAO;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiChiFragment extends Fragment {
    FloatingActionButton addLoaiChi;
    EditText et_AddLoaiChi;

    SwipeRefreshLayout swipeRefreshLayout;

    //RecyclerView
    RecyclerView recyclerViewLoaiChi;
    ArrayList<Mangement_loaiChi> listLoaiChi = new ArrayList<Mangement_loaiChi>();
    //RecyclerView

    loaiChiDAO loaiChiDAO;
    Mangement_loaiChi loaiChi;

    //search
    private SearchView searchView;
    private SearchView.OnQueryTextListener queryTextListener;
    View view;
    AdapterLoaiChi adapter;
    //search

    public LoaiChiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loai_chi, null);

        //refresh
        //refresh();
        //refresh

        //add
        addData();
        //add

        //RecycleView
        capnhatGiaodien();
        //Recycle view

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
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
        //even view dialog add loai chi
        addLoaiChi = view.findViewById(R.id.addLoaiChi);
        addLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                LayoutInflater inf = LoaiChiFragment.this.getLayoutInflater();
                View view1 = inf.inflate(R.layout.dialog_loaichi, null);
                et_AddLoaiChi = view1.findViewById(R.id.et_AddLoaiChi);

                alertDialog.setView(view1);

                alertDialog.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tenLoaiChi = et_AddLoaiChi.getText().toString();
                        //SQLite
                        loaiChi = new Mangement_loaiChi(tenLoaiChi);
                        loaiChiDAO = new loaiChiDAO(getContext());
                        loaiChiDAO.AddLoaiChi(loaiChi);
                        //SQLite
                        capnhatGiaodien();
                        Toasty.success(getContext(), "Đã thêm!", Toast.LENGTH_SHORT, true).show();

                    }
                });
                alertDialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.error(getContext(), "Đã hủy", Toast.LENGTH_SHORT, true).show();
                    }
                });

                alertDialog.show();
            }
        });
        //even add dialog add loai chi
    }

    public void capnhatGiaodien() {

        recyclerViewLoaiChi = view.findViewById(R.id.recyclerViewLoaiChi);
        loaiChiDAO = new loaiChiDAO(view.getContext());

        //View Loai Thu
        listLoaiChi = loaiChiDAO.ViewLoaiChi();
        //View Loai Thu

        recyclerViewLoaiChi.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new AdapterLoaiChi(listLoaiChi, getContext(), this);

        recyclerViewLoaiChi.setLayoutManager(linearLayoutManager);
        recyclerViewLoaiChi.setAdapter(adapter);
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
//                        capnhatGiaodien();
//                        TastyToast.makeText(getContext(), "Hoàn tất!", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
//                        //Toasty.success(getContext(), " Đã cập nhật! ", Toast.LENGTH_SHORT, true).show();
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
//        //refreshLayout
//    }
}
