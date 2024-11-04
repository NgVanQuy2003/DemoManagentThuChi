package com.example.admin.demomanagentthuchi.ViewPager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.demomanagentthuchi.KhoanThuFragment;
import com.example.admin.demomanagentthuchi.LoaiThuFragment;
import com.example.admin.demomanagentthuchi.ThuFragment;

public class ThuViewPager extends FragmentStatePagerAdapter {


    public ThuViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new LoaiThuFragment();
                break;
            case 1:
                fragment = new KhoanThuFragment();
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
