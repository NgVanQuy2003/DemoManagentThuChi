package com.example.admin.demomanagentthuchi;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.demomanagentthuchi.ViewPager.ThuViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThuFragment extends Fragment {
    public ViewPager pagerThu;
    public TabLayout tabLayoutThu;

    public ThuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu, null);
        pagerThu = view.findViewById(R.id.pagerThu);
        tabLayoutThu = view.findViewById(R.id.tabLayoutThu);

        //...add name and icon
        tabLayoutThu.addTab(tabLayoutThu.newTab().setText("Loại thu"));
        tabLayoutThu.addTab(tabLayoutThu.newTab().setText("Khoản Thu"));
        tabLayoutThu.setTabGravity(TabLayout.GRAVITY_FILL);

        //...even
        pagerThu.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutThu));
        tabLayoutThu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerThu.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //...Do adapter
        ThuViewPager thuViewPager = new ThuViewPager(getActivity().getSupportFragmentManager());
        pagerThu.setAdapter(thuViewPager);
        //

        return view;
    }


}
