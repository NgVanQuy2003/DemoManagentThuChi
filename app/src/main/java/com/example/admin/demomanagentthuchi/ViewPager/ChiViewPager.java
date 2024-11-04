package com.example.admin.demomanagentthuchi.ViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.demomanagentthuchi.KhoanChiFragment;
import com.example.admin.demomanagentthuchi.LoaiChiFragment;

public class ChiViewPager extends FragmentStatePagerAdapter {

    public ChiViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment1;
        switch (position) {
            case 0:
                fragment1 = new LoaiChiFragment();
                break;

            case 1:
                fragment1 = new KhoanChiFragment();
                break;
            default:
                return null;

        }

        return fragment1;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
