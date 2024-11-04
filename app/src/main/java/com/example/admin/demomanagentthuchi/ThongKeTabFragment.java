package com.example.admin.demomanagentthuchi;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.demomanagentthuchi.ViewPager.ThongKeTabPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeTabFragment extends Fragment {
    public ViewPager pagerThongKeTab;
    public TabLayout tabThongKe;

    public ThongKeTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_tab, null);
        pagerThongKeTab = view.findViewById(R.id.pagerThongKeTab);
        tabThongKe=view.findViewById(R.id.tabThongKe);

        tabThongKe.addTab(tabThongKe.newTab().setText(" Thu chi"));
        tabThongKe.addTab(tabThongKe.newTab().setText(" Loại thu"));
        tabThongKe.addTab(tabThongKe.newTab().setText(" Loại chi"));

        pagerThongKeTab.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabThongKe));
        tabThongKe.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerThongKeTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        //Do adapter
        ThongKeTabPager adapter=new ThongKeTabPager(getActivity().getSupportFragmentManager());
        pagerThongKeTab.setAdapter(adapter);

        return view;
    }

}
